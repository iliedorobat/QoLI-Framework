# eLIF: European Life Index Framework

## Requirements
- JDK 11 or OpenJDK 11.

## Setup
1. Download and install [JDK 11](https://www.oracle.com/nl/java/technologies/javase/jdk11-archive-downloads.html) or [OpenJDK 11](https://openjdk.org/install/) (or newer versions)

2. Download and install [Maven 3.x](https://maven.apache.org/install.html)

3. Clone the repository:
```bash
git clone https://github.com/iliedorobat/QoLI-Framework.git
```

4. Update environment variables and app constants:
- `AUTH_USER` and `AUTH_PASSWORD`: credentials used for updating the datasets (calling `/api/v2/stats/collect` API)
- `HOST_ADDRESS`: the IP address of the host
- `IS_PRODUCTION`: `true` if the app is deployed on the production server
- `IS_TESTING`: `true` for downloading sample data instead of the full set
- `KEY_STORE_FILE`: path to the `keystore.p12` file
- `KEY_STORE_PASS`: password for the `keystore.p12` file
- `USE_TOMCAT_SERVER`: `true` if the app is deployed on Apache Tomcat
- `Constants.BASE_PATH` contains the main path to the project. This path should be updated if the app is deployed on the production server.

5. [OPTIONAL] Run the following scripts on the production environment:
   1. Set the same value for `KEY_STORE_PASS` in `src/main/bash/certbot_post_renewal_hook.sh` as the value of the environment variable with the same name
   2. Create JKS certificate:
    ```bash
    sudo src/main/bash/certbot_post_renewal_hook.sh
    ```
   3. Enroll renewal hook to certbot:
    ```bash
    src/main/bash/certbot_enroll_renewal_hook.sh
    ```
   4. Copy the startup script:
    ```bash
    src/main/bash/elife_enroll_startup.sh
    ```

6. [OPTIONAL] Install deps & compile the project (this step is already done in the next step):
- Production build:
    ```bash
    sudo ./gradlew clean build && ./gradlew copyProd && ./gradlew copyEnv
    ```
- Dev build:
    ```bash
    ./gradlew clean build
    ```

7. Create the server daemon:
    1. [OPTIONAL] Remove the server daemon:
    ```bash
    pm2 flush elife && pm2 delete elife
    ```
    2. Create and start a background process:
    ```bash
    pm2 start ~/workplace/automation/elife_startup.sh --name=life-index
    ```
    3. Check if the server is up and running:
    ```bash
    curl -i -X GET "https://life-index.eu:8443"
    ```
    ```bash
    curl -i -X GET "https://life-index.eu:8443/qoli/api/v2/stats/config?analysisType=aggregate"
    ```
    ```bash
    curl -i -X GET "https://life-index.eu:8443/qoli/api/v2/stats?analysisType=individually&aggr=education:dropoutRatio&startYear=2020&endYear=2022"
    ```
    ```bash
    curl -i -X GET "https://life-index.eu:8443/qoli/api/v2/stats?analysisType=aggregate&aggr=education:dropoutRatio&aggr=health:health:bodyMassIndex&startYear=2020&endYear=2022"
    ```
    ```bash
    curl -i -X GET "https://life-index.eu:8443/qoli/api/v2/stats/collect?username=admin&password=admin1234"
    ```

8. Collect the datasets:
```bash
java -jar elife.jar --collect
```

9. Aggregate the datasets:
   1. Calculate QoLI dimensions:
    ```bash
    java -jar elife.jar --calculate --calculateIndicators --direction=COLUMN
    ```
    2. Calculate QoLI based on a specific set of indicators:
    ```bash
    java -jar elife.jar --calculate --aggr=["discussionRatio","gettingTogetherFrdRatio"]
    ```

## Print Data
1. Print QoLI and QoLI dimensions:
    ```bash
    java -jar elife.jar --print --direction=COLUMN --seriesType=COUNTRY --dimension=QOLI
    java -jar elife.jar --print --direction=COLUMN --seriesType=REGION --dimension=QOLI
    ```
2. Print specific indicators:
    ```bash
    java -jar elife.jar --print --direction=COLUMN --seriesType=COUNTRY --dimension=EDUCATION --indicator=DIGITAL_SKILLS_RATIO
    java -jar elife.jar --print --direction=COLUMN --seriesType=REGION --dimension=EDUCATION --indicator=DIGITAL_SKILLS_RATIO
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
