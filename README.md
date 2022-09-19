# QoLI-Framework
Quality of Life Index Framework

## Local setup
### Requirements
- JDK 11 or OpenJDK 11.

### Setup
1. Clone the repository:
```bash
git clone https://github.com/iliedorobat/QoLI-Framework.git
```
2. Navigate to the root directory (QoLI-Framework directory).
3. Register the environment variables:
```bash
export QOLI_PATH=`pwd`
export CLASSPATH="${QOLI_PATH}/out/production/QoLI-Framework:${QOLI_PATH}/lib/poi-5.2.0.jar:${QOLI_PATH}/lib/guava-22.0.jar:${QOLI_PATH}/lib/xmlbeans-5.0.3.jar:${QOLI_PATH}/lib/httpcore-4.4.11.jar:${QOLI_PATH}/lib/poi-ooxml-5.2.0.jar:${QOLI_PATH}/lib/protonpack-1.13.jar:${QOLI_PATH}/lib/guava-stream-1.0.jar:${QOLI_PATH}/lib/httpclient-4.5.9.jar:${QOLI_PATH}/lib/log4j-api-2.17.1.jar:${QOLI_PATH}/lib/commons-io-2.11.0.jar:${QOLI_PATH}/lib/log4j-core-2.17.1.jar:${QOLI_PATH}/lib/assertj-core-3.6.1.jar:${QOLI_PATH}/lib/assertj-json-1.0.0.jar:${QOLI_PATH}/lib/mockito-core-2.2.8.jar:${QOLI_PATH}/lib/commons-logging-1.2.jar:${QOLI_PATH}/lib/commons-math3-3.6.1.jar:${QOLI_PATH}/lib/jackson-core-2.8.11.jar:${QOLI_PATH}/lib/json-stat-java-0.2.2.jar:${QOLI_PATH}/lib/commons-compress-1.21.jar:${QOLI_PATH}/lib/commons-collections4-4.3.jar:${QOLI_PATH}/lib/jackson-databind-2.8.11.1.jar:${QOLI_PATH}/lib/jackson-annotations-2.8.11.jar:${QOLI_PATH}/lib/jackson-datatype-jdk8-2.8.11.jar:${QOLI_PATH}/lib/jackson-datatype-guava-2.8.11.jar:${QOLI_PATH}/lib/jackson-datatype-jsr310-2.8.11.jar"
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
java app/java/Main --calculate --direction=COLUMN
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
* List of dimensions: https://github.com/iliedorobat/QoLI-Framework/src/app/java/commons/constants/DimensionNames.java
* List of indicators: https://github.com/iliedorobat/QoLI-Framework/src/app/java/commons/constants/IndicatorNames.java

## Main Database
https://ec.europa.eu/eurostat/data/database

## API Server
https://ec.europa.eu/eurostat/wdds/rest/data/v2.1/json/en/ dataset_name

## GUI
https://appsso.eurostat.ec.europa.eu/nui/show.do?dataset= dataset_name
