# 🌱⚔️ Elden Thing: A Text-Based Roguelike in Java

```
`7MM"""YMM  `7MMF'      `7MM"""Yb. `7MM"""YMM  `7MN.   `7MF'    MMP""MM""YMM `7MMF'  `7MMF'`7MMF'`7MN.   `7MF' .g8"""bgd  
  MM    `7    MM          MM    `Yb. MM    `7    MMN.    M      P'   MM   `7   MM      MM    MM    MMN.    M .dP'     `M  
  MM   d      MM          MM     `Mb MM   d      M YMb   M           MM        MM      MM    MM    M YMb   M dM'       `  
  MMmmMM      MM          MM      MM MMmmMM      M  `MN. M           MM        MMmmmmmmMM    MM    M  `MN. M MM           
  MM   Y  ,   MM      ,   MM     ,MP MM   Y  ,   M   `MM.M           MM        MM      MM    MM    M   `MM.M MM.    `7MMF'
  MM     ,M   MM     ,M   MM    ,dP' MM     ,M   M     YMM           MM        MM      MM    MM    M     YMM `Mb.     MM  
.JMMmmmmMMM .JMMmmmmMMM .JMMmmmdP' .JMMmmmmMMM .JML.    YM         .JMML.    .JMML.  .JMML..JMML..JML.    YM   `"bmmmdPY  
```

A Dark Souls–inspired roguelike played entirely in the terminal. You're a **farmer** in a blighted land: cure the rot, grow magical plants, trade with wandering merchants, win over NPCs and take on the **Bed of Chaos**, a boss that literally grows as you fight it.

```
...xxxx...xxxxxx.....#####.....xxx......
...xxx....xxxxxxx....#_@_#.....xx.......
..xxxx...xxxxxxxxx...#_Ao#....xx........
xxxxx...xxxxxxxxxx...##_##...xxx.......x
xxxxx..xxxxxxxxxxx....ybm..xxxxx......xx
```
*The game world rendered in ASCII. `@` is you.*

---

## ✨ Features

- **Creatures with personalities**: Omen Sheep, Spirit Goats and Golden Beetles that wander, follow, reproduce, lay eggs or go berserk, each driven by swappable behaviours
- **Farming**: plant seeds and grow living plants (Inheritree, Bloodrose and more) through growth stages, and cure Crimson Rot from blighted soil
- **NPC affection system**: give gifts to NPCs, who react with different dialogue depending on how much they like you, and reward you with potions or a secret legendary weapon
- **Trading**: buy and sell items with runes from merchants who keep their own stock
- **Weapons and combat**: Broadsword, Katana, Dragonslayer Greatsword and more, each with its own effects
- **Boss fight**: the Bed of Chaos grows new branches and leaves during battle and drops a trophy when defeated
- **Status effects and consumables**: healing, stamina, attribute buffs and debuffs, and spawn effects
- **Teleportation circles** for travelling between areas

---

## 🏗️ Design

Built on a turn-based game engine, with the game layer designed around **SOLID** principles and reusable abstractions:

| Concept | How it's modelled |
|---|---|
| Behaviours | Pluggable AI (`WanderBehaviour`, `FollowBehaviour`, `BerserkBehaviour`…) chosen by priority |
| Capabilities | Interfaces like `Sellable`, `Consumable`, `Growable`, `Curable`, `Giveable` instead of type checks |
| Effects | Composable effects (`ModAttributeEffect`, `SpawnActorEffect`, `HealingEffect`…) attached to items and actions |
| Conditions | Reusable conditions (`NearbyCondition`, `AffectionComparisonCondition`…) that drive dialogue and rewards |

UML class and sequence diagrams and design rationales are in [`docs/design`](./docs/design).

---

## 🚀 Run it

**Requires Java 17+.**

```bash
javac -d out $(find src -name "*.java")
java -cp out game.Application
```

Or open the project in IntelliJ IDEA and run `game.Application`.

---

## 👥 Team & my role

Built by a team of five. **I was the top contributor (66 commits).** My work included:
- The **NPC affection system**: gifting, affection levels, dynamic monologues and rewards
- **Consumables** (healing potion, stamina tonic) and the `Consumable` / `ConsumeAction` design
- The secret **Demon King's Daggers** weapon and the **boss trophy** drop
- Javadoc across the codebase, plus UML and sequence diagrams

## 🌿 Other branches
- `early-version`: my solo prototype of the game's first version
- `alt-copy`: an alternative build with eating, gifting and potion mechanics
