---
layout: page
title: Dasco Gabriel's Project Portfolio Page
---

### Project: NetworkUS

NetworkUS is a desktop app for organizing contacts that features seamless group management, allowing for quick searches of contacts with desired traits (i.e specialization) and hence project group formation. NetworkUS is optimised for CLI users, complemented with GUI to display the results.

Given below are my contributions to the project.

* **New Feature**: Added `showtags` command (PR [\#84](https://github.com/AY2122S1-CS2103T-T10-3/tp/pull/84))
  * What it does: Shows all tags that are present in the contact book.
  * Justification: This feature is necessary for the users to know what tags that they have created. It helps a lot when they want to filter out certain tags that they need by using the `find` command.

* **New Feature**: Added `alias` command (PR [\#99](https://github.com/AY2122S1-CS2103T-T10-3/tp/pull/99))
  * What it does: Creates an alias for existing command.
  * Justification: This feature is necessary as our target users prefer to use CLI than GUI. Hence, speed is one of their top priority. Creating alias can help them to work more efficiently by customizing the existing command to suit their own needs.
  * Credits:
    * [CommanderW324](https://ay2122s1-cs2103t-t10-3.github.io/tp/team/commanderw324.html), who helped implement other supporting alias command, such as `showalias` and `deletealias`.
    * [e0543403](https://ay2122s1-cs2103t-t10-3.github.io/tp/team/e0543403.html), who suggested extremely simple bug fix. (PR [\#163](https://github.com/AY2122S1-CS2103T-T10-3/tp/pull/163))

* **Code contributed**: [Reposense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=dystoriax&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=DystoriaX&tabRepo=AY2122S1-CS2103T-T10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project Management**:
  * Finalized Project version release workflow (along with [samyipsh](https://ay2122s1-cs2103t-t10-3.github.io/tp/team/samyipsh.html))
  * Reviewed 7 PRs ([\#80](https://github.com/AY2122S1-CS2103T-T10-3/tp/pull/80), [\#82](https://github.com/AY2122S1-CS2103T-T10-3/tp/pull/82), [\#97](https://github.com/AY2122S1-CS2103T-T10-3/tp/pull/97), [\#108](https://github.com/AY2122S1-CS2103T-T10-3/tp/pull/108), [\#110](https://github.com/AY2122S1-CS2103T-T10-3/tp/pull/110), [\#159](https://github.com/AY2122S1-CS2103T-T10-3/tp/pull/159), [\#164](https://github.com/AY2122S1-CS2103T-T10-3/tp/pull/164))

* **Enhancement to existing features**:
  * **New Fields**: Added `detail` field (PR [\#52](https://github.com/AY2122S1-CS2103T-T10-3/tp/pull/52))
    * What it does: Additional field to store detail of a person
    * Justification: Numerous contacts make it hard for user to remember particular thing about a person
  * **Automated Tests**: Added automated tests for `detail`, `showtags`, and `alias` commands.

* **Documentation**:
  * User Guide:
    * `showtags` and `alias` command usage, refined by other team members
    * Revised introduction of NetworkUS product as well as formatting errors
  * Developer Guide:
    * NFR
    * Glossary
    * Implementation of `alias` command

