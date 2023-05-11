# check3rs
check3rs is a battle of the bots, specifically bots which can play checkers!


## Status :exclamation:
check3rs is currently incomplete. Classes need documentation, [Check3rs.java](game/Check3rs.java) is missing logic to run the game, and a class representing moves needs implementation. The current version was pushed for others to start building code in the meantime.

## How to Start
In the [bots package](bots), create a new class which extends [BotInterface.java](game/BotInterface.java). Make sure to implement the setColor() and move() methods, the documentation for which will be completed on [BotInterface.java](game/BotInterface.java) in the near future. For an example, check out [WalliBot.java](bots/WalliBot.java).

## Reading the Board
The board is represented by a 2D array of [Stone.java](game/Stone.java) objects. Each [Stone.java](game/Stone.java) object has methods for accessing information, which will be documented in the [Stone.java class](game/Stone.java). Empty squares will be null. Boards passed through the move() method will be rotated so your bot's color starts in rows indexed 5-7.