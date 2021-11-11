---
layout: page
title: User Guide
---

NetworkUS is a desktop app for organizing contacts. It features seamless **group management**, allowing for **quick searches of contacts with desired traits** (i.e specialization) and hence **project group formation**. It is catered for **Computer Science Students**. The app is optimized for the **Command Line Interface (CLI)** to allow swift access for keyboard users. The app is also complemented with **Graphical User Interface (GUI)** to display the program.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Before you read

This user guide is for users who want to use NetworkUS for their daily use or developer who wants to find out what this app does before contributing to the project. This user guide will guide you on how to use NetworkUS to manage your contacts.

## How to use the guide

If this is the first time you are using NetworkUS, please refer to the [quick start](#quick-start) section to set up and start using NetworkUS.

If you are looking for a general overview of the commands refer to the [command summary](#command-summary) section. 

If you are unclear of what the command formats mean in the various explanations refer to the [features](#features) section.

If you are unsure of the meaning of any terms within this user guide, please refer to the [glossary](#glossary).

To jump to any particular section please refer to the table of contents found at the top of this guide.


## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `networkus.jar` from [here](https://github.com/AY2122S1-CS2103T-T10-3/tp/releases/download/v1.4/networkus.jar).

1. Copy the file to the folder you want to use as the _home folder_ for your NetworkUS app.

1. Double-click the file to start the app. The GUI below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press `enter` to execute it. e.g. typing **`help`** and pressing `enter` will open the help window.<br>
   Some example commands you can try:

   * **`list`**: Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com`: Adds a contact named `John Doe` to the NetworkUS.

   * **`delete`**`3`: Deletes the 3rd contact shown in the current list.

   * **`clear`**: Deletes all contacts.

   * **`exit`**: Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## User Interface

![User Interface](images/UIDetails.png)

The image above shows the UI Components: 
1. Menu Bar: shows the 'Help' and 'Show Alias' option.
1. Command Bar: location for entering commands.
1. Result Display: shows result feedback from commands.
1. Contact List: shows the current contacts.
1. Footer: shows file path of the storage file and the total number of contacts.


<div style="page-break-after: always;"></div>

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters with **prefixes** can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter with **prefix** is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Additional parameters for commands that do not take in parameters will be ignored. (such as `help`, `list`, `exit` and `clear`)<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* INDEXES allows multiple INDEX to be entered with each INDEX being separated with a space.<br>
  e.g. if you specify `tag 1 2 3 cs2030`, it will tag first, second and third contacts in the displayed contacts list.

</div>

### Viewing help: `help`

Provides functionality to access the help page in the browser.


![help message](images/helpMessage.png)

Format: `help` <br/>
Keyboard shortcut alternative: `f1` then press `enter`


### Adding a contact: `add`

Adds a contact to the list of contacts. You cannot add duplicate contacts in the contact book.

Format: `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [g/GITHUB_USERNAME] [l/LINKEDIN] [d/DETAIL] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** <br>

* A contact can have any number of tags (including 0) <br>

* View the [duplicate-detection](#duplicate-detection) section to find out what is considered as a duplicate contact. <br>

* View the [valid-field](#valid-fields) section to find out what is considered as valid field.

</div>

Examples:
* `add n/Johnny Sim p/91234567`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com p/1234567 t/criminal g/lovelyprisoner`

### Listing all contacts: `list`

Shows a list of all contacts in the [global contacts](#global-contacts) and the total number of contacts.

Format: `list`

### Editing a contact: `edit`

Edits an existing contact in the list of contacts.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [g/GITHUB_USERNAME] [l/LINKEDIN] [d/DETAIL] [t/TAG]…​`

* Edits the contact at the specified `INDEX`. The index refers to the index number shown in the [displayed contacts](#displayed-contacts) list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the contact will be replaced.
* You can remove all the contact’s tags by typing `t/` without specifying any tags after it. The same applies for description `d/`.
* Edited contact should not result in duplicate contacts in the contact book.


Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st contact to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd contact to be `Betsy Crower` and clears all existing tags.
*  `edit 2 p/ g/` Removes the phone number and github user of the 2nd contact.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** <br>

* You can remove specified fields by typing the field's prefix only. This applies for all fields except Name. <br>

* View the [duplicate-detection](#duplicate-detection) section to find out what is considered as a duplicate contact. <br>

* View the [valid-field](#valid-fields) section to find out what is considered as valid field.

</div>

### Finding contacts: `find`

Find contacts whose names or tags contain any of the given keywords from the list of contacts.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The command is not case-sensitive, `find john` will match `John`
* Currently, only the name and their tags are searched.
* Contacts whose names or tags match at least one keyword will be returned.
  `find john smith` will return `John Fletcher` and `Smith George`

Examples:
* `find John` will return `john`, `John Doe` and `Aaron john`
* `find jonas aaron silva` will return `jonas`, `Aaron john` and `Ann Silva`
* `find friends` will return anyone whose names or tags have the word `friends` in them

### Opening a field of a contact: `open`

Opens the LinkedIn/Github page from details of field specified of a particular contact from the NetworkUS contacts.

Format: `open INDEXES FIELD`

* The index contained in indexes refers to the index number shown in the [displayed contacts](#displayed-contacts) list.
* The index **must be a positive integer** 1, 2, 3, …​
* The Field must be `github` or `linkedin`

Examples:
* `list` followed by `open 2 3 4 github` opens the Github page of the 2nd, 3rd, and 4th contact of the [displayed contacts](#displayed-contacts) list.
* `find Betsy` followed by `open 1 linkedin` opens the LinkedIn page of the 1st contact in the results of the `find` command

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This will redirect you to a web browser with the requested LinkedIn/Github page.
</div>

### Deleting a contact: `delete`

Deletes the specified contact from the NetworkUS contacts.

Format: `delete INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the [displayed contacts](#displayed-contacts) list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd contact in the [displayed contacts](#displayed-contacts) list.
* `find Betsy` followed by `delete 1` deletes the 1st contact in the results of the `find` command.

<div style="page-break-after: always;"></div>

### Showing contact: `show`

Shows the specified contact's full contact information in a separate window. Will show any truncated details or fields that are too long. <br>

![show window](images/showcommandwindow.png)

Format: `show INDEX`

* Index must be the index that is in the currently visible list.

Example:
* `show 1` will show the full details of the contact with the specified index.



### Clearing all entries: `clear`

Clears all entries from the [global contacts](#global-contacts) list.

Format: `clear`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This will clear all contacts data in NetworkUS !
</div>


### Exiting the program: `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Tagging features

You can use tags to categorize your contacts as you wish. You can tag them based on their modules taken, field of specialization or personality. This would help when you want to find suitable teammate for projects, assignments, or hackathons. For instance, you can tag certain contacts with OS to indicate that the person has a specialization in operating systems. You can then find all contacts that have a specialization in OS by using the command `find OS`. The following are details of the tagging-related commands.

### Tagging contacts: `tag`

Tags the specified contacts from the NetworkUS contacts with the specified tag.

Format: `tag INDEXES TAG`

* Tags the contacts at the specified `INDEXES`.
* The index refers to the index number shown in the [displayed contacts](#displayed-contacts) list.
* The indexes **must be positive integers** 1, 2, 3, …​
* Tag **must consist of letters and/or numbers without spaces**.
* Will not add duplicate tags to contacts.

Examples:
* `list` followed by `tag 1 2 programmer` tags the 1st and 2nd contacts in the [global contacts](#global-contacts) list with a programmer tag.
* `find John` followed by `tag 1 2 programmer` tags the 1st and 2nd contacts in the results of the `find` command with a programmer tag.


### Tagging displayed contacts: `tagall`

Tags all [displayed contacts](#displayed-contacts) in the NetworkUS contacts with the specified tag.

Format: `tagall TAG`

* Tags all [displayed contacts](#displayed-contacts) with the specified `TAG`.
* Will not add duplicate tags and will run successfully even if all [displayed contacts](#displayed-contacts) already have the tag.
* The tag **must consist of letters and/or numbers without spaces** e.g. programmer, CS2103T

Examples:
* `list` followed by `tagall programmer` tags all contacts in the [global contacts](#global-contacts) list with the tag `programmer`.
* `find Alex` followed by `tagall CS2103T` tags all contacts in the results of the `find` command with the tag `CS2103T`.

### Untagging contacts: `untag`

Delete the specified tag from the specified contact from the NetworkUS contacts.

Format: `untag INDEXES TAG`

* Deletes the tag from the contact at the specified `INDEXES`.
* The index refers to the index number shown in the [displayed contacts](#displayed-contacts) list.
* The indexes **must be positive integers** 1, 2, 3, …​
* Tag **must consist of letters and/or numbers without spaces**.

Example:
* `list` followed by `untag 1 2 friend` deletes friend tag from 1st and 2nd contacts in the [global contacts](#global-contacts) list.
* `find John` followed by `untag 1 2 friend` deletes friend tag from the 1st and 2nd contacts in the results of the `find` command.

### Untagging displayed contacts: `untagall`

Deletes the specified tag from all [displayed contacts](#displayed-contacts) in NetworkUS.

Format: `untagall TAG`

* Deletes specified `TAG` from all [displayed contacts](#displayed-contacts).
* Tag **must consist of letters and/or numbers without spaces**.

Example:
* `list` followed by `untagall CS2040` deletes CS2040 tag from all [global contacts](#global-contacts).

### Replace tag from all contacts: `replacetag`

Replaces the specified tag from all [displayed contacts](#displayed-contacts) in NetworkUS with a new tag.

Format: `replacetag TAG1 TAG2`

* Replaces `TAG1` with `TAG2` for all contacts.
* `TAG1` is the tag to be replaced.
* `TAG2` is the new tag to replace `TAG1`.
* Tags **must consist of letters and/or numbers without spaces**.

Example:
* `list` followed by `replacetag CS2040 CS2030` replaces CS2040 tag to CS2030 tag from all [global contacts](#global-contacts).

### Showing tags: `showtags`

Shows all tags that are used in NetworkUS contacts.

Format: `showtags`

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Alias Features

Alias helps you create more customized commands. You can shorten the existing commands, or even completely rename it with other names. Here are example use cases of alias:
* Create `tag -A` as an alias for `tagall` command
* Create `tag -D` as an alias for `untag` command

The following are details of the alias-related commands

### Aliasing commands: `alias`

Creates an alias for existing NetworkUS command.

Format: `alias COMMAND ALIAS`

* Creates an alias `ALIAS` for command `COMMAND`.
* `ALIAS` can contain spaces, but cannot be empty.
* `COMMAND` must be an existing command in NetworkUS (e.g. `list`, `tagall`, `untag`, etc.)
* Aliases that you create will be saved.
* NetworkUS only replace your input with corresponding commands if the alias matches the **prefix word(s)** of your input.
  * For example: the alias `tag -A` will match the command `tag -A parameter`, but `tag -AD parameter` **will not be matched** even though `tag -A` matches the prefix of words `tag -AD`.
* NetworkUS will check the **longest matching alias** in your input and change it with the corresponding command.
  * For example: Suppose that you have created two aliases:
      * `tag -A` as command `tagall`
      * `tag -A -D` as command `untagall`
  * The command `tag -A -D OS` will be replaced as `untagall OS` because the longest matching alias that matches the prefix word is `tag -A -D`.

Example:
* `alias tagall tag -A` will create an alias `tag -A` for the `tagall` command, allowing `tag -A` to represent `tagall`.
* `alias tagall tag` will throw an error as `tag` is an existing NetworkUS command. **You cannot create alias as an existing command name**.
* `alias notACommand tag -A` will throw an error as `notACommand` is not an existing NetworkUS command. **You are only able to create an alias for existing commands**.
* `alias tag add n/Your Name` will create alias `add n/Your Name` for the `tag` command. Note that this kind of alias is not recommended. See the **caution** section.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Use it carefully. You are encouraged to create appropriate aliases. Refrain from creating alias that resembles existing command syntax, such as create alias `add n/Your Name` as any `tag` or `add` or any other commands. 
**NetworkUS will not handle these cases for you.**
</div>

### Showing saved aliases: `showalias`

Shows all aliases stored in the user preferences in a new window. <br>

![show alias window](images/showaliaswindow.png)

Format: `showalias`
Keyboard Shortcut: `F2`

Example:
* `showalias` will show the aliases in a new window.


### Dealiasing commands: `deletealias`

Deletes an existing alias.

Format: `deletealias ALIAS`

* Deletes the specified alias.
* `ALIAS` must be an existing alias in NetworkUS.

Example:
* `deletealias tag -A` will remove the alias `tag -A`.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Data Storage

### Saving the data

Contact list data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


### Editing the data file

Contact data is saved as a JSON file `[JAR file location]/data/contactbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, NetworkUS will discard all data and start with an empty data file the next time its run.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ContactBook home folder.<br>

**Q**: How do I import existing contacts to NetworkUS?<br>
**A**: Unfortunately, NetworkUS does not support importing contact from external sources. You have to put in the contacts one by one.<br>

**Q**: Any tips on managing my contacts?<br>
**A**: You can tag your contacts with their fields of expertise, personalities, etc. You can use the [find](#finding-contacts-find) command to find contacts with desired traits.<br>

**Q**: How can I work faster?<br>
**A**: You can [alias](#aliasing-commands-alias) an existing commands with a shorter alias. Example: `ta` for the `tagall` command.<br>

**Q**: Can non CS students use this application?<br>
**A**: Yes. While the fields and functionalities for this release are designed for CS students, anyone can use it to store contacts as a generic contact book management software.<br>

**Q**: Why is the data stored offline?<br>
**A**: Ease of storage accessibility and security reasons.<br>

**Q**: Can I use NetworkUS on my mobile phone?<br>
**A**: NetworkUS can only be used on Java-supported mobile phones.<br>

**Q**: Why are my alias preferences empty / not updating?<br>
**A**: Try moving the NetworkUS app to a new empty folder and running the jar file again.

**Q**: How do I form a group using the app?<br>
**A**: You can group contacts together under the same tag by using the [tag](#tagging-contacts-tag) function and specifying their indexes.<br>

**Q**: Why can't I make an alias using some specific words?<br>
**A**: Refer to the section on examples under the [alias](#aliasing-commands-alias) command to see what words are not available for use as an alias.<br>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Miscellaneous

### Duplicate detection

**Checked fields**: `Phone`, `Email`, `Github` and `Linkedin`

A contact is considered a duplicate in any of the below cases:
* All of that contact's **Checked fields** are empty, and there is another contact with the same name and also having all their **Checked fields** empty.
* That contact has the same name as another contact, and also at least one identical non-empty **Checked field**. 


<div markdown="span" class="alert alert-primary">:exclamation: **Note:**
The equality check for the names of 2 contacts disregards capitalization and spaces in the names being compared. 
</div>

### Valid Fields

Below are the requirements for what constitutes as valid for the fields in NetworkUS.

Field | Format, Examples
--------|------------------
**Name** | Only letters and/or numbers and spaces <br> e.g., `Sam Yeo`
**Tag** | Letters and/or numbers without spaces <br> e.g., `CS2103T` , `programmer`
**Phone** |  Only numbers at least 3 digits long <br> e.g., `999` , `62353535`
**Email** | `local-part` + @ + `domain-name` <br> `local-part` contains letters, numbers and special characters excluding `+` `-` `_` `.`, cannot start or end with special characters <br> `domain-name` consists of `domain labels` seperated by periods <br> `domain labels` consist of at least 2 letters and/or numbers <br> `domain labels` excluding the last `domain label` can have non-consecutive hyphens but cannot start or end with a hyphen <br> e.g., `berniceyu@example.com`
**Github** | Only letters, numbers and hyphens <br> Cannot start or end with a hyphen or have multiple consecutive hyphens <br> Has a maximum length of 39 characters <br> e.g., `Alexio`
**LinkedIn** | Any valid LinkedIn URL <br> e.g., `https://www.linkedin.com/in/benson/`
**Details** | Anything you can type in <br> e.g., `Quite literally anything you can type in.`

### Valid index

An index is valid if it is a **positive** integer.<br>
Example of valid indexes: `1`, `2`, `3`, ...<br>
Example of invalid indexes: `-1`, `0`, `*`, `+1`, ...

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Glossary

### Displayed contacts

All contacts **displayed** in NetworkUS application.

Most of the commands in NetworkUS act on the currently viewable list. Thus, whenever there are mentions of Index used in Commands it refers to the index of the currently displayed contacts list.<br>

Here `delete 3` will delete "Charlotte Oliviero"<br>
![Original](images/Ui.png)

<div style="page-break-after: always;"></div>

Here `delete 3` will show an error message as 3 is not shown (Even though it is in the original list).
![filtered](images/findList.png)

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Most NetworkUS commands will execute on displayed contacts.
</div>

### Global contacts

All contacts that are **stored** in the NetworkUS.

### Field

A field is specific information of a contact in NetworkUS's contact list like phone number.

<div style="page-break-after: always;"></div>

### JavaScript Object Notation (JSON)

JSON is the file format that NetworkUS uses to store the contacts.

### Graphical User Interface (GUI)

A GUI is an interface that uses visual indicator to interact with the system.

### Command Line Interface (CLI)

A CLI is a text-based interface that uses text input to interact with the system.

### Command

A command is an instruction that you can use in NetworkUS to perform a certain task.

### Prefix

Prefixes are unique identifiers in front of parameters which are used by NetworkUS to identify which fields the values belong to.

### Alias

Aliases are alternative words that you can use to represent the standard commands that NetworkUS uses.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Command summary

Action | Format, Examples
--------|------------------
[**Add**](#adding-a-contact-add) | `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [g/GITHUB_USERNAME] [l/LINKEDIN] [d/DETAIL] [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com t/friend t/colleague`
[**Alias**](#aliasing-commands-alias) | `alias COMMAND ALIAS`<br> e.g., `alias tagall tag -A`
[**Clear**](#clearing-all-entries-clear) | `clear`
[**Delete**](#deleting-a-contact-delete) | `delete INDEX`<br> e.g., `delete 3`
[**Delete alias**](#dealiasing-commands-deletealias) | `deletealias ALIAS`<br> e.g., `deletealias tag -A`
[**Edit**](#editing-a-contact-edit) | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [g/GITHUB_USERNAME] [l/LINKEDIN] [d/DETAIL] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
[**Find**](#finding-contacts-find) | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
[**Help**](#viewing-help-help) | `help`
[**Open**](#opening-a-field-of-a-contact-open) | `open INDEXES FIELD` <br> e.g., `open 1 linkedin`
[**List**](#listing-all-contacts-list) | `list`
[**Replace tag**](#replace-tag-from-all-contacts-replacetag) | `replacetag TAG1 TAG2`<br> e.g., `replacetag friend enemy`
[**Show alias**](#showing-saved-aliases-showalias) | `showalias`
[**Show tags**](#showing-tags-showtags) | `showtags`
[**Tag**](#tagging-contacts-tag) | `tag INDEXES TAG` <br> e.g., `tag 1 2 programmer`
[**Tag all**](#tagging-displayed-contacts-tagall) | `tagall TAG` <br> e.g., `tagall programmer`
[**Untag**](#untagging-contacts-untag) | `untag INDEXES TAG`<br> e.g., `untag 1 2 friend`
[**Untag all**](#untagging-displayed-contacts-untagall) | `untagall TAG`<br> e.g., `untagall friend`

