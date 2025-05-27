# FIT2099 Assignment (Semester 1, 2025)

```
`7MM"""YMM  `7MMF'      `7MM"""Yb. `7MM"""YMM  `7MN.   `7MF'    MMP""MM""YMM `7MMF'  `7MMF'`7MMF'`7MN.   `7MF' .g8"""bgd  
  MM    `7    MM          MM    `Yb. MM    `7    MMN.    M      P'   MM   `7   MM      MM    MM    MMN.    M .dP'     `M  
  MM   d      MM          MM     `Mb MM   d      M YMb   M           MM        MM      MM    MM    M YMb   M dM'       `  
  MMmmMM      MM          MM      MM MMmmMM      M  `MN. M           MM        MMmmmmmmMM    MM    M  `MN. M MM           
  MM   Y  ,   MM      ,   MM     ,MP MM   Y  ,   M   `MM.M           MM        MM      MM    MM    M   `MM.M MM.    `7MMF'
  MM     ,M   MM     ,M   MM    ,dP' MM     ,M   M     YMM           MM        MM      MM    MM    M     YMM `Mb.     MM  
.JMMmmmmMMM .JMMmmmmMMM .JMMmmmdP' .JMMmmmmMMM .JML.    YM         .JMML.    .JMML.  .JMML..JMML..JML.    YM   `"bmmmdPY  
```

[Contribution Log](https://docs.google.com/spreadsheets/d/1u7E528AFSbaStaeHvKo21yjCL0mlMMHHsZlLZGSJI0Y/edit?usp=sharing)

![img.png](A3-REQ3-uml.png)

```
The image above is the UML diagram for REQ3

REQ3. Affection level system. For instance, the farmer can give items to the NPCs, which will increase or 
decrease their affection level towards the farmer.

Brief explanation of the six new classes added in the game package:

GiveAction: A specific action for giving items.
Affection (interface): Defines a contract for how items can affect affection.
AffectionItem (abstract): A base class for all items that can influence NPC affection, implementing the 
                          Affection interface.
ThornedBloom: A concrete AffectionItem.
SacredTear: Another concrete AffectionItem.
BossTrophy: Also a concrete AffectionItem.

AffectionItem and Interface (Technical Detail)
As a Developer,
I want AffectionItem to implement the Affection interface,
So that all AffectionItems are guaranteed to have a mechanism for affecting affection.

Story:
The AffectionItem abstract class implements the Affection interface. 
This ensures that any concrete AffectionItem (such as "ThornedBloom", "SacredTear", or "BossTrophy" ) will inherently 
possess the necessary methods (defined by the Affection interface) to influence an NPC's affection level when a 
GiveAction occurs.

Affection Level System Scenarios
Scenario 1: Giving an AffectionItem (Positive Impact)
As a Player,
I want to give an AffectionItem to an NPC,
So that their affection level towards me increases.

Story:
The player, "Farmer", approaches Sellen. "Farmer" has a "Sacred Tear" in his inventory. He opens the interaction menu 
and selects the "give" option. From his inventory, he chooses the "Sacred Tear" and presents it to Sellen. 
Sellen's eyes light up, and a notification appears: "Sellen's Affection Level +5!" Sellen's dialogue changes to 
reflect her increased fondness for "Farmer".

Scenario 2: Giving an AffectionItem (Negative Impact)
As a Player,
I want to give an AffectionItem to an NPC,
So that their affection level towards me decreases (if the item is disliked).

Story:
The player, "Farmer", approaches Guts, who is in a berserk state. "Farmer" has a "ThornedBloom" in his inventory, 
hoping it might appease him. He opens the interaction menu and selects the "give" option. From his inventory, 
he chooses the "ThornedBloom" and presents it to Guts. Guts snarls, his eyes glowing with rage, and a notification 
appears: "Guts's Affection Level -10!" Guts's dialogue becomes hostile, reflecting his displeasure with "Farmer".
```

![img.png](A3-REQ4-uml.png)

```
The image above is the UML diagram for REQ4

REQ4. Realistic farming system. For instance, plants need to be watered, grounds can be shovelled, etc.

Brief explanation of the five new classes added in the game package:

WaterAction: A specific action for watering crops.
WaterContainer (abstract): A base class for all water containers that can be used to water crops.
DemonJug: A concrete WaterContainer that hurts the Player when used, it is not usable on crops blessed by grace.
HolyGrail: A concrete WaterContainer that heals the Player and spawns a Spirit Goat when used, it is blessed by grace.
CursedPot: A concrete WaterContainer that swaps the Player's stamina and health (both max and current value) when used,
           it is cursed.

WaterContainer (Technical Detail)
As a Developer,
I want WaterContainer to extend the abstract class Item,
So that all WaterContainers are guaranteed to have functionality for being picked up, dropped, and 
held by an actor in its inventory.

Realistic Farming System Scenarios
Scenario 1: Watering an Inheritree with the HolyGrail
As a Player,
I want to water to an Inheritree with the HolyGrail.

Story:
The player, "Farmer", approaches an Inheritree. "Farmer" has a "Holy Grail" in his inventory. He opens the interaction 
menu and selects the "Water Inheritree with Holy Grail" option. The "Farmer" is healed and a Spirit Goat is spawned 
within his surroundings, the Inheritree is watered.

Scenario 2: Watering an Inheritree with the DemonJug
As a Player,
I want to water to an Inheritree with the DemonJug.

Story:
The player, "Farmer", approaches an Inheritree. "Farmer" has a "Demon Jug" in his inventory. He opens the interaction 
menu and selects the "Water Inheritree with Demon Jug" option. 
A message is displayed "Demon Jug is unable to water Inheritree as it is blessed by grace", indicating that the water 
action has failed.
```