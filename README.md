# QoLI-Framework

The **QoLI-Framework** is a data-driven framework for collecting, processing,
aggregating, and analyzing European quality-of-life indicators. It provides
the data processing infrastructure behind the [European Life Index platform](https://life-index.eu/).

This repository contains the Python implementation of the QoLI-Framework,
including its data processing components and REST API.

## Requirements

- Python 3.12 or newer
- [uv](https://docs.astral.sh/uv/) (recommended for Python environment management)
- [PM2](https://pm2.keymetrics.io/) (optional, for production process management)
- Nginx (optional, for production deployments)

## Setup

### 1. Clone the repository

```bash
git clone https://github.com/iliedorobat/QoLI-Framework.git
cd QoLI-Framework
```

### 2. Create the Python environment

Create a virtual environment:

```bash
uv venv
```

Activate it:

```bash
source .venv/bin/activate
```

Install the project dependencies:

```bash
uv sync
```

### 3. Configure environment variables

Copy the example environment configuration:

```bash
cp .env.example .env
```

Edit `.env` and configure the application settings according to your environment.

The configuration may include:

| Variable | Description |
|---|---|
| `AUTH_USER` | Username used for protected data collection operations |
| `AUTH_PASSWORD` | Password used for protected data collection operations |
| `IS_PRODUCTION` | Enables production-specific application settings |
| `IS_TESTING` | Enables testing mode, using sample datasets instead of the full datasets |

Additional configuration options may be defined in `.env.example`.

### 4. Run the API server

The application uses FastAPI and Uvicorn.

Start the development server:

```bash
uv run uvicorn framework.api.server:app \
    --host 127.0.0.1 \
    --port 8027 \
    --reload
```

Alternatively, use the startup script:

```bash
./scripts/elife_start.sh
```

### 5. Production deployment

For production deployments, the API can be managed using PM2.

Start the application:

```bash
pm2 start scripts/elife_start.sh \
    --name life-index \
    --interpreter bash
```

Save the PM2 process configuration:

```bash
pm2 save
```

Check the application status:

```bash
pm2 status
```

View application logs:

```bash
pm2 logs life-index
```

The production deployment uses Nginx as a reverse proxy, handling 
HTTPS termination and forwarding API requests to the Uvicorn server.

SSL certificates are managed through Certbot. No Java keystore or 
certificate conversion is required.

### 6. Verify the API

Once the server is running, verify the following endpoints.

**API root:**

```bash
curl -i http://127.0.0.1:8027/
```

**QoLI configuration:**

```bash
curl -i -X GET \
    "http://127.0.0.1:8027/qoli/api/v2/stats/config?analysisType=aggregate"
```

**Individual statistics:**

```bash
curl -i -X GET \
    "http://127.0.0.1:8027/qoli/api/v2/stats?analysisType=individually&aggr=education:dropoutRatio&startYear=2020&endYear=2022"
```

**Aggregated statistics:**

```bash
curl -i -X GET \
    "http://127.0.0.1:8027/qoli/api/v2/stats?analysisType=aggregate&aggr=education:dropoutRatio&aggr=health:health:bodyMassIndex&startYear=2020&endYear=2022"
```

For production deployments, replace the local URL with the public API address:

```text
https://life-index.eu/qoli/api/v2/stats
```

## Data Collection and Processing

QoLI-Framework provides functionality for collecting and processing the datasets 
used in the [European Life Index platform](https://life-index.eu/).

The data processing workflow consists of the following stages:

1. **Data collection** – retrieve the source datasets from their corresponding providers.
2. **Data preparation** – clean, transform, and normalize the collected datasets.
3. **Indicator calculation** – calculate the individual quality-of-life indicators.
4. **Aggregation** – compute the QoLI dimensions and the overall Quality of Life Index.
5. **Data serialization** – prepare the resulting statistics for API consumption.

The framework exposes data collection and statistical processing functionality 
through its Python modules and API endpoints.

## API

The REST API is implemented using FastAPI.

The main API modules include:

| Module | Description |
|---|---|
| `collector_router` | Dataset collection operations |
| `geo_router` | Geographic information and related operations |
| `stats_router` | Statistical data and QoLI results |
| `stats_schema_router` | Statistical configuration and schema information |

The main statistics endpoints are:

| Endpoint | Description |
|---|---|
| `/qoli/api/v2/stats` | Retrieve individual or aggregated statistics |
| `/qoli/api/v2/stats/config` | Retrieve statistical configuration |
| `/qoli/api/v2/stats/collect` | Trigger dataset collection |

Protected operations use HTTP authorization headers.

## Project Structure

```text
QoLI-Framework/
│
├── framework/
│   ├── api/
│   │   └── server.py
│   ├── ...
│
├── scripts/
│   └── elife_start.sh
│
├── tests/
│
├── .env.example
├── .gitignore
├── requirements.txt
└── README.md
```

## Development

Run the application in development mode:

```bash
uv run uvicorn framework.api.server:app \
    --host 127.0.0.1 \
    --port 8027 \
    --reload
```

## License

See the repository for licensing information.

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
