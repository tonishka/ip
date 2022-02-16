# User Guide

Yoda is a Star Wars themed personal task management application. 

## Features 

Yoda models each task in your list as a Quest. The more Quests you finish, the closer you get to defeating the Dark Lord of Sith.

### Add Quests

Add a Quest to your list. Quests can be of three types:
1. Deadline - Quests that are bound by a definitive deadline.
2. Event - Quests that take place during a specific time period.
3. Todo - Simple Quests that can be accomplished anytime.

### Delete Quests

Entered an incorrect Quest? Simply delete it!

### Find Quests

Can't remember the name of your Quest? Find Quests in your list that match a keyword.

### Mark Quests

Finished a Quest? Hurrah! Mark it as done in your list. 

Something came up? No worries, mark a Quest as in-progress.

### Update Quests

Something about the Quest changed? Simply update it.

### View logs

View a log of all your Quests.

## Usage

### `log`

Prints a log of all your Quests. 

Format:
`log`

### `todo`

Creates a new Quest of type Todo with the given description.

Format:
`todo [TODO_DESCRIPTION]`

Example:
`todo laundry`

### `event`

Creates a new Quest of type Event with the given description and time period.

Format:
`event [EVENT_DESCRIPTION]/[EVENT_TIME_PERIOD]`

Example:
`event piano recital/4-6pm tomorrow`

### `deadline`

Creates a new deadline bound Quest with the given description and deadline.

Format:
`deadline [DEADLINE_DESCRIPTION]/YYYY-MM-DD HH:mm`

Example:
`deadline Biology Quiz/2022-02-28 23:59`

### `mark`

Marks the given incomplete Quest as complete.

Format:
`mark [QUEST_NUMBER]`

Example:
`mark 7`

### `unmark` 

Marks the given complete Quest as incomplete.

Format:
`unmark [QUEST_NUMBER]`

Example:
`unmark 2`

### `delete`

Deletes the given Quest.

Format:
`delete [QUEST_NUMBER]`

Example:
`delete 1`

### `help`

Redirects you to the User Guide.

Format:
`help`

### `find`

Finds all Quests that contain the given search term.

Format:
`find [KEYWORD]`

Example:
`find Quiz`

### `update_desc`

Updates the description of the given Quest.

Format:
`update_desc [QUEST_NUMBER]`

- Yoda will prompt you to enter a new description for you Quest.

Example:
`update_desc 1`

### `update_period`

Updates the time period of the given Event-style Quest.

Format:
`update_period [QUEST_NUMBER]`

- This command only works if the given QUEST_NUMBER is an Event.
- Yoda will prompt you to enter a new time period for your Event.

Example:
`update_period 2`

### `update_deadline`

Updates the deadline of the given Deadline type Quest.

Format:
`update_deadline [QUEST_NUMBER]`

- This command only works if the given QUEST_NUMBER is a Deadline.
- Yoda will prompt you to enter a new deadline for your Deadline.

Example:
`update_deadline 1`
