---
layout: page
title: Samuel Yip's Project Portfolio Page
---

### Project: NetworkUS

NetworkUS is a desktop app for organizing contacts that features seamless group management, allowing for quick searches of contacts with desired traits (i.e specialization) and hence project group formation. NetworkUS is optimised for CLI users, complemented with GUI to display the results.

Given below are my contributions to the project:

* **New Feature**: Added the Open Field command. Provides the ability to open in the browser the github or linkedin pages of a contact or multiple contacts at once. (Pull requests [\#93](https://github.com/AY2122S1-CS2103T-T10-3/tp/pull/93))
  * What it does: Opens the LinkedIn/Github page from details of field specified of a particular person(s) from the NetworkUS contacts.
  * Justification: This feature is necessary because it increases the convenience of the app by giving users a means to open the stored github and linkedin profile information of their contacts instead of copying them one by one and pasting them in. This would be especially useful for group formation when a user would like to compare the suitability of multiple contacts at once through looking at their profile pages.
  * Highlights:
    * This enhancement uses a reusable function to open url in user's default browser. This new reusable function can be easily used by other commands or extended to open other fields.
    * Specific url was generated from contacts' github username or linkedin url depending on field indicated to be open. (Pull Request [\#77](https://github.com/AY2122S1-CS2103T-T10-3/tp/pull/77))
    * UML Activity and UML Sequence diagram created and added to Developer Guide.


* **New Field**: Added a Github field for a person. User can update this field in generic add/edit commands.
  * Credits: use of this github validation regex [github-username-regex](https://github.com/shinnn/github-username-regex)


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=samyipsh&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)


* **Project management**:
  * Project admin: `v1.1` - `v1.4` (3 releases) on GitHub
  * Finalized Project version release workflow (along with [dystoriax](https://ay2122s1-cs2103t-t10-3.github.io/tp/team/dystoriax.html))


* **Enhancements to existing features**:
  * Designed project's vector icon / logo in Inkscape (Pull requests [\#66](https://github.com/AY2122S1-CS2103T-T10-3/tp/pull/66/files))

![Ui](../images/networkUS_icon.png)

  * GUI addition of field names for clarity (Pull request [\#63](https://github.com/AY2122S1-CS2103T-T10-3/tp/pull/63/files))


* **Documentation**:
  * User Guide:
    * Added documentation for the features `open` command
  * Developer Guide:
    * Added implementation details of the `open` command.
    * Added UML Sequence Diagram & Activity Diagram of `open` command.
    * Product Scope
    * User Stories
  * General reformatting and organisation (Pull Request [\#85](https://github.com/AY2122S1-CS2103T-T10-3/tp/pull/85/files))


* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#84](https://github.com/AY2122S1-CS2103T-T10-3/tp/pull/84)
  * Reported bugs and suggestions for other teams in the class (15 bugs reported in PE-D)
