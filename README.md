# eLIF: European Life Index Framework



## Local setup
### Requirements
- JDK 11 or OpenJDK 11.

### Setup
1. Clone the repository:
```bash
git clone https://github.com/iliedorobat/QoLI-Framework.git
```
2. Open the Terminal/Command Prompt and navigate to the root directory (`QoLI-Framework` directory).
3. Register the environment variables:
```bash
export QOLI_PATH=`pwd`
export CLASSPATH="${QOLI_PATH}/out/production/QoLI-Framework:${QOLI_PATH}/lib/poi-5.2.0.jar:${QOLI_PATH}/lib/guava-22.0.jar:${QOLI_PATH}/lib/xmlbeans-5.0.3.jar:${QOLI_PATH}/lib/httpcore-4.4.11.jar:${QOLI_PATH}/lib/poi-ooxml-5.2.0.jar:${QOLI_PATH}/lib/protonpack-1.13.jar:${QOLI_PATH}/lib/guava-stream-1.0.jar:${QOLI_PATH}/lib/httpclient-4.5.9.jar:${QOLI_PATH}/lib/log4j-api-2.17.1.jar:${QOLI_PATH}/lib/commons-io-2.11.0.jar:${QOLI_PATH}/lib/log4j-core-2.17.1.jar:${QOLI_PATH}/lib/assertj-core-3.6.1.jar:${QOLI_PATH}/lib/assertj-json-1.0.0.jar:${QOLI_PATH}/lib/mockito-core-2.2.8.jar:${QOLI_PATH}/lib/commons-logging-1.2.jar:${QOLI_PATH}/lib/commons-math3-3.6.1.jar:${QOLI_PATH}/lib/jackson-core-2.15.3.jar:${QOLI_PATH}/lib/json-stat-java-0.2.2.jar:${QOLI_PATH}/lib/commons-compress-1.21.jar:${QOLI_PATH}/lib/commons-collections4-4.3.jar:${QOLI_PATH}/lib/jackson-databind-2.15.3.jar:${QOLI_PATH}/lib/jackson-annotations-2.15.3.jar:${QOLI_PATH}/lib/jackson-datatype-jdk8-2.15.3.jar:${QOLI_PATH}/lib/jackson-datatype-guava-2.15.3.jar:${QOLI_PATH}/lib/jackson-datatype-jsr310-2.15.3.jar"
```
4. Compile the project:
```bash
javac -d ./out/production/QoLI-Framework src/app/java/BuildProject.java
java app/java/BuildProject
```
5. Collect the datasets:
```bash
java app/java/Main --collect
```
6. Calculate the QoLI dimensions (only once):
```bash
java app/java/Main --calculate --calculateIndicators --direction=COLUMN
```
7. Print the QoLI and the QoLI dimensions:
```bash
java app/java/Main --print --direction=COLUMN --seriesType=COUNTRY --dimension=QOLI
java app/java/Main --print --direction=COLUMN --seriesType=REGION --dimension=QOLI
```
7. Print specific indicators:
```bash
java app/java/Main --print --direction=COLUMN --seriesType=COUNTRY --dimension=EDUCATION --indicator=DIGITAL_SKILLS_RATIO
java app/java/Main --print --direction=COLUMN --seriesType=REGION --dimension=EDUCATION --indicator=DIGITAL_SKILLS_RATIO
```
* List of dimensions: https://github.com/iliedorobat/QoLI-Framework/src/app/java/commons/constants/FilePathConst.java (constants suffixed by "_DIR")
* List of indicators: https://github.com/iliedorobat/QoLI-Framework/src/app/java/commons/constants/IndicatorNames.java



## More info
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





TODO: search for "Dataset contains no data"
TODO: search for "Lack of data:"
https://ec.europa.eu/eurostat/statistics-explained/index.php?title=EU_statistics_on_income_and_living_conditions_(EU-SILC)_methodology_-_environment_of_the_dwelling
