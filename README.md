# eLIF: European Life Index Framework



## Local Setup
### Requirements
- JDK 11 or OpenJDK 11.

### Setup
1. Clone the repository:
```bash
git clone https://github.com/iliedorobat/QoLI-Framework.git
```

2. Update environment variables and app constants:
- `AUTH_USER` and `AUTH_PASSWORD`: credentials used for updating the datasets (calling `/api/v2/stats/collect` API)
- `HOST_ADDRESS`: the IP address of the host
- `IS_PRODUCTION`: `true` if the app is deployed on the production server
- `IS_TESTING`: `true` for downloading sample data instead of the full set
- `KEY_STORE_FILE`: path to the `keystore.p12` file
- `KEY_STORE_PASS`: password for the `keystore.p12` file
- `USE_TOMCAT_SERVER`: `true` if the app is deployed on Apache Tomcat

- `Constants.BASE_PATH` contains the main path to the project. This path should be updated if the app is deployed on the production server.

3. [OPTIONAL] Run the following scripts on the production environment:
   1. Create JKS certificate:
    ```bash
    src/main/bash/certbot_post_renewal_hook.sh
    ```
   2. Enroll renewal hook to certbot:
    ```bash
    src/main/bash/certbot_enroll_renewal_hook.sh
    ```
   3. Copy the startup script:
    ```bash
    src/main/bash/qoli_enroll_startup.sh
    ```

4. Install deps & compile the project:
```bash
mvn clean install -Denv.type=ENV_TYPE -Ddirectory=DIRECTORY_PATH
```
- ENV_TYPE **(OPTIONAL)** = `dev` or `prod`
- DIRECTORY_PATH **(OPTIONAL)** = path to the target directory where the compiled files will be placed
- E.g.:
    ```bash
    mvn clean install -Denv.type=prod
    ```
    ```bash
    mvn clean install -Denv.type=dev -Ddirectory=/home/my_user/workplace/QoLI-Framework/target
    ```
  
5. Create the server daemon:
    1. [OPTIONAL] Remove the server daemon:
    ```bash
    pm2 delete qoli && pm2 flush qoli
    ```
    2. Create and start a background process:
    ```bash
    pm2 start ~/workplace/automation/qoli_startup.sh
    ```
    2. Check if the server is up and running:
    ```bash
    curl -i -X GET "https://webdata.ro:8443"
    ```
    ```bash
    curl -i -X GET "https://webdata.ro:8443/qoli/api/v2/stats/config?analysisType=aggregate"
    ```
    ```bash
    curl -i -X GET "https://webdata.ro:8443/qoli/api/v2/stats?analysisType=individually&aggr=education:dropoutRatio&startYear=2020&endYear=2022"
    ```
    ```bash
    curl -i -X GET "https://webdata.ro:8443/qoli/api/v2/stats?analysisType=aggregate&aggr=education:education:dropoutRatio&aggr=health:health:bodyMassIndex&startYear=2020&endYear=2022"
    ```
    ```bash
    curl -i -X GET "https://webdata.ro:8443/qoli/api/v2/stats/collect?username=admin&password=admin1234"
    ```

6. Collect the datasets:
```bash
java -jar qoli.jar --collect
```

7. Aggregate the datasets:
   1. Calculate QoLI dimensions:
    ```bash
    java -jar qoli.jar --calculate --calculateIndicators --direction=COLUMN
    ```
    2. Calculate QoLI based on a specific set of indicators:
    ```bash
    java -jar qoli.jar --calculate --aggr=["discussionRatio","gettingTogetherFrdRatio"]
    ```

### Print Data
1. Print QoLI and QoLI dimensions:
    ```bash
    java -jar qoli.jar --print --direction=COLUMN --seriesType=COUNTRY --dimension=QOLI
    java -jar qoli.jar --print --direction=COLUMN --seriesType=REGION --dimension=QOLI
    ```
2. Print specific indicators:
    ```bash
    java -jar qoli.jar --print --direction=COLUMN --seriesType=COUNTRY --dimension=EDUCATION --indicator=DIGITAL_SKILLS_RATIO
    java -jar qoli.jar --print --direction=COLUMN --seriesType=REGION --dimension=EDUCATION --indicator=DIGITAL_SKILLS_RATIO
    ```

### Run Server
```bash
java -jar qoli.jar --server
```



## Framework Info
* List of dimensions:
  * [QoLIAggrParams.AGGR_PARAMS](https://github.com/iliedorobat/QoLI-Framework/blob/release/2.1/src/ro/webdata/qoli/aggr/stats/dimensions/QoLIAggrParams.java#L42)
* List of indicators:
  * Economic and Physical Safety: [SafetyAggrParams.AGGR_PARAMS](https://github.com/iliedorobat/QoLI-Framework/blob/release/2.1/src/ro/webdata/qoli/aggr/stats/dimensions/safety/SafetyAggrParams.java#L46)
  * Education: [EducationAggrParams.AGGR_PARAMS](https://github.com/iliedorobat/QoLI-Framework/blob/release/2.1/src/ro/webdata/qoli/aggr/stats/dimensions/education/EducationAggrParams.java#L32)
  * Governance and Basic Rights: [GovRightsAggrParams.AGGR_PARAMS](https://github.com/iliedorobat/QoLI-Framework/blob/release/2.1/src/ro/webdata/qoli/aggr/stats/dimensions/gov/GovRightsAggrParams.java#L31)
  * Health: [HealthAggrParams.AGGR_PARAMS](https://github.com/iliedorobat/QoLI-Framework/blob/release/2.1/src/ro/webdata/qoli/aggr/stats/dimensions/health/HealthAggrParams.java#L52)
  * Leisure and Social Interactions: [LeisureInteractAggrParams.AGGR_PARAMS](https://github.com/iliedorobat/QoLI-Framework/blob/release/2.1/src/ro/webdata/qoli/aggr/stats/dimensions/leisureInteract/LeisureInteractAggrParams.java#L54)
  * Material Living Conditions: [MaterialLivingAggrParams.AGGR_PARAMS](https://github.com/iliedorobat/QoLI-Framework/blob/release/2.1/src/ro/webdata/qoli/aggr/stats/dimensions/materialLiving/MaterialLivingAggrParams.java#L42)
  * Natural and Living Environment: [EnvironmentAggrParams.AGGR_PARAMS](https://github.com/iliedorobat/QoLI-Framework/blob/release/2.1/src/ro/webdata/qoli/aggr/stats/dimensions/environment/EnvironmentAggrParams.java#L29)
  * Overall Experience of Life: [OverallExperienceAggrParams.AGGR_PARAMS](https://github.com/iliedorobat/QoLI-Framework/blob/release/2.1/src/ro/webdata/qoli/aggr/stats/dimensions/overall/OverallExperienceAggrParams.java#L20)
  * Productive or Main Activity: [MainActivityAggrParams.AGGR_PARAMS](https://github.com/iliedorobat/QoLI-Framework/blob/release/2.1/src/ro/webdata/qoli/aggr/stats/dimensions/mainActivity/MainActivityAggrParams.java#L44)



## Data Source
### Main Database
https://ec.europa.eu/eurostat/data/database

### API Server
https://ec.europa.eu/eurostat/api/dissemination/statistics/1.0/data/ __dataset_name__?__query_params__

### GUI
https://ec.europa.eu/eurostat/databrowser/view/ __dataset_name__ /default/table?lang=en



## Publications:
SYSMEA 2019: [eLIF: European Life Index Framework - An Analysis for the Case of European Union Countries](https://www.thinkmind.org/index.php?view=article&articleid=sysmea_v12_n34_2019_5)
```
@InProceedings{sysmea_v12_n34_2019_5,
    author="Dorobat, Ilie Cristian and Posea, Vlad",
    title="eLIF: European Life Index Framework - An Analysis for the Case of European Union Countries",
    booktitle="2019 International Journal On Advances in Systems and Measurements (SYSMEA)",
    volume="12"
    number="3-4"
    year="2019",
    publisher="IARIA",
    pages="198-214",
    issn="1942-261x"
}
```

ICDS 2019: [Quality of Life Index Analysis for the Case of Romanian Regions](http://www.thinkmind.org/index.php?view=article&articleid=icds_2019_2_30_10030)
```
@InProceedings{icds_2019_2_30_10030,
    author="Dorobat, Ilie Cristian, Rinciog, Octavian, Muraru, George Cristian and Posea, Vlad",
    title="Quality of Life Index Analysis for the Case of Romanian Regions",
    booktitle="2019 The Thirteenth International Conference on Digital Society and eGovernments (ICDS)",
    year="2019",
    publisher="IARIA",
    pages="37-44",
    isbn="978-1-61208-685-9"
    issn="2308-3956"
}
```
