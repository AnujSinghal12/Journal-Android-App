# Journal

Author - Anuj Singhal

BITSID - 2019A7PS0039G

email - f20190039@goa.bits-pilani.ac.in

Project - Journal App

***

## What does the app do?

It is an app where the user can store information about what activity they have done on a particular day from a particular time time to another. This Entry is stored and can later be edited, deleted and shared as text.

***

## Known bugs

When a new Entry is made the page where user can enter the date, start time and end time is shown. The texts on the buttons is supposesd to be "DATE", "START TIME", "END TIME". But these texts show up only for a fraction of a second and are then replaced by buttons with empty string as the text. The buttons work as expected and after selecting the values display them in the correct way. I was not able to resolve this bug, so I have instead initialised the desired strings as the values of the entry parameters to avoid the button being invisible.

In this project I faced quite a lot of issues and had to use online resources a lot. This lead to me having to fix a lot of bugs. I think I've fixed most of them other than the one mentioned above.

***

## How to do it?

1. Since most of the actions have already been defined in the nav-graph xml, all I had to to was use those actions in the parts where I have to go from one fragment to the other. Then for the info page, I added the 'help' fragment and the required action in the EntryListFragment of the xml. I added an argument to the timepickerdialog which would help me determine if the dialog will return the value for the start time button or end time button. This method allows me to not need to have a shared view model. Regardless I've added the required functions to use the shared view model.

2. I was able to reuse a lot of the code from the slides. For adding the new columns I initially wanted to go with Time and Date datatypes, but in the end ended up going with String. To implement delete, I added the function in JournalEntryDao and referenced it in JournalRepository and EntryDetailViewModel. 

3. I added two menu xml files (one for EntryList will come later). The menu to be used in the EntryDetailsFragment needs to contail the delete and the share buttons. I've commented out the code using which we can see the info button on from their too. In the EntryDetailFragment I've called the alert dialog and set the required parameters according to the selection of yes or no.

4. Similar to the delete button, I've added code in the EntryDetails Fragment to implement the share button. I'm adding the given string using putExtra function. I've taken inspiration from a stackoverflow answer to implement this functionality.

5. First I created the layout for the info page. Then in the nav graph create the element for the help fragment and I added the action to navigate to it in the entryListFragement. Creating the fragment is very similar to what we've done in a2-DiceGames.

6. I've not run the Accessibility checks using espresso.

***

## Testing 

During manual testing the app crashed multiple times. These crashed occured when I did not specify the type in the intent used for sharing the text, and in a lot of the other places where I've used code from stackoverflow.

***

## Hours to Complete

25 hours

***

## Difficulty

9.5/10

I found the assignment to be very difficult so naturally I ended up spending a lot of time on it and learing a lot of different ways to do the same things.
