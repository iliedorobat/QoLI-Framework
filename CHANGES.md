# Release notes
All notable changes to this project will be documented in this file.
This project adheres to [Semantic Versioning](http://semver.org/).
---

## 2.1.1
- Added maven support
- Added "/qoli" prefix to APIs

## 2.1
- Added the server service.

## 2.0
- "percentageSafetyDouble" and "reverseGenderGap" methods were removed and the gender pay/emp gap are calculated based on the "percentageReverseRatio" method.
- Changed current indicators:
  - "Pupils Ratio" was reversed because it describes a negative state.
  - "Alcoholic Ratio" was changed to depict the "frequency of heavy episodic drinking" instead of the "daily alcohol consumption".
  - Inactive young people param was changed from Y18-24 to Y15-29.
  - Body Mass Index formula was changed to describe the share of the people within normal limits.
  - "Hospital Beds" and "Total Personnel" are reported to 1 million inhabitants instead of 100.000.
  - "Health Personnel" formula it also includes midwives. 
  - "Fruits and Vegetables Ratio" formula has been changed to depict the proportion of the population who never consumes fruits and vegetables.
  - "Non-Participation" to voluntary and social activities formula was updated.
  - "Gender Gap" formula was updated.
  - "Population Trust Ratio" formula was updated.
  - EU Parliament elections & Presidential elections were added to "Voter Turnout".
  - "Frequency contact" indicators have been consolidated into a single indicator.
  - "Non-Participation to cultural, sports and voluntaries activities" indicators have been consolidated into a single indicator.
  - "Participation to cultural, sports and voluntaries activities" indicators have been consolidated into a single indicator.
- Deleted indicators:
  - "Over Qualified Ratio" was removed because there is data only for 2014 and in some cases (DK, IE, NL) there is not data at all.
- New indicators:
    - Added back the "Air Pollution Ratio": env_ac_ainah_r2 (CH4, CO, NH3, NMVOC, NOX, PM2.5 and PM10).
    - Added "Low Work Intensity Ratio" to "Productive or Main Activity" dimension.
    - Share of people who faced depressive symptoms.
    - Working Flexible Time Ratio.
    - GDP per Capita Ratio.
    - Population participating in education and training in the 12 months.
    - People who frequently contact family, relatives or friends.
    - Population rating the satisfaction with recreational and green areas as high.
- New features:
  - Allow the calculation of the QoLI based on a custom set of indicators (E.g.: ["discussionRatio","gettingTogetherFrdRatio"]).

## 1.2
- "Leisure" and "Social Interactions" dimensions were merged back into a single dimension - "Leisure and Social Interactions".
- Added support for calculating the indicators that make up the 8+1 QoLI dimensions.

## 1.1
- Removed sdg_11_50 (air pollution) since the dataset has discontinued since 20/11/2023.
- Updated crim_off_cat (crime ratio) with the following indicators: bribery, computers, criminalGroups, corruption, fraud, moneyLaundering, sexualExploitation.
- Overloading the percentageSafetyDouble method to support "reversed calculation".
- Add a new printing methods.
- Fix the writer of regions.

## 1.0
- First stable release.
