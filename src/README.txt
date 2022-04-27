I used absolute file paths. You must change them to run the code.

In Main > UI, change FILE_PATH to the location of the 'res' folder.
This folder contains the images used in the game.

In FileHandling > FileActions, change FILE_PATH to the location of the 'FileHandling' folder.
This is where the window that allows you to select a file will open by default.
It is also where the save game mechanic accesses the empty NewSave.txt.

Format for game save files:
energy, itemNum [number of items in player inventory], item1Name, item2Name, item3Name, debt, wallet, location [enum name]

To play checkers, type the letter-number identifier (i.e, AO) of a square into the box and click submit. Possible moves
will be highlighted in green; type the name of any green square to move the piece. It is two player, so you
will have to move for the other side as well.