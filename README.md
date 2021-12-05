# Sokoban
This is a Sokoban game with a graphical UI (JavaFX).

## Usage
After launching, user has to type the level name. The game comes
with two built-in levels, namely `@lvl1` and `@lvl2`. However,
it's possible to read an external file too. The level file
specification is described in a further section.

To move a player, use arrow keys. Control your character to
push the boxes onto the target fields. The game ends when all
the crates are on desired positions. But beware, even though you
can push the boxes, it's impossible to pull them back!

You can exit the game at any time, by pressing ESC or closing
the window.

## Level file format
The level files are plain text files. Every character describes
one field:
* `#` means wall
* `x` means target
* `o` means box
* `*` means box that's on target
* `@` is the player
* any other character would be interpreted as an empty field

## Assets
The assets for this game were made by 
[Kenney](https://www.kenney.nl/assets/sokoban). These are under
the CC0 license, which means anyone can use and modify them for
any purpose.