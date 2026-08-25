# Visual Novel Engine Demo

A personal Java project exploring the design and implementation of a **data-driven visual novel engine**.

The engine separates story data from game logic, allowing dialogue, characters, choices, branching paths, flags, and player statistics to be defined externally using YAML files rather than being hard-coded into the game.

> This project is primarily a learning and experimentation project focused on game-engine architecture, state management, branching narrative systems, and data-driven design.

## Features

* **Data-driven dialogue**

  * Story content is defined in YAML files.
  * Dialogue does not need to be hard-coded into Java classes.

* **Branching dialogue**

  * Dialogue nodes can transition into other nodes.
  * Player choices can determine the next part of the story.

* **Choices**

  * Choices can modify the game state.
  * Choices can add flags and modify player statistics.

* **Game state**

  * Supports persistent story flags.
  * Supports integer-based statistics such as trust, honesty, and stress.

* **Character states**

  * Characters can have different positions and emotional states.
  * Character information is stored alongside dialogue.

* **Conditional routes**

  * Story progression can depend on previously acquired flags.

* **Extensible node system**

  * Dialogue, choice, and conditional nodes are represented as separate node types.
  * The node-based architecture is intended to make additional story mechanics easier to implement.

## Example Story Structure

The engine represents a visual novel as a collection of interconnected nodes.

```text
intro
  │
  ▼
morning
  │
  ▼
conversation
  │
  ▼
first_choice
  ├───────────────┐
  ▼               ▼
honesty_path    lie_path
                    │
                    ▼
                aftermath
```

A choice can modify the `GameState` before moving to another node.

For example:

```yaml
choices:
  - text: "Tell her you remember nothing"
    next: honesty_path
    flags:
      - amnesia
    stats:
      trust: 2
      honesty: 1

  - text: "Lie and say you’re fine"
    next: lie_path
    stats:
      trust: -1
      stress: 1
```

This allows the narrative to maintain information about the player's previous decisions and use that information later.

## Architecture

The engine is built around a small set of core components.

### `VNEngine`

The central engine responsible for:

* Tracking the current story node
* Managing the collection of nodes
* Managing the current `GameState`
* Moving between nodes

### `Node`

The base abstraction for story events.

Current node types include:

* `NodeDialogue`
* `NodeChoice`
* `NodeConditional`

This makes the story effectively behave like a directed graph, where each node represents a point in the narrative.

### `GameState`

Stores persistent information about the player's progression.

```text
GameState
├── Flags
│   ├── amnesia
│   └── ...
│
└── Stats
    ├── trust
    ├── honesty
    └── stress
```

### `Choice`

Represents a player decision and its effects on the game state.

A choice can:

* Navigate to another node
* Add flags
* Modify statistics

### `YamlParser` / `NodeLoader`

Story data is loaded from YAML and converted into the engine's Java objects.

This keeps narrative content separate from the engine implementation.

## Story Data

A simple dialogue node can be written as:

```yaml
id: conversation
type: dialogue
next: first_choice

lines:
  - "Her words linger longer than they should."

  - text: "Do you remember what happened?"
    characters:
      - name: Alice
        position: LEFT
        expression: CURIOUS
```

The engine parses this data into Java objects at runtime.

This approach makes it possible to modify the story without changing the engine's source code.

## Project Structure

```text
.
├── src/
│   └── main/
│       ├── java/
│       │   ├── CharacterState.java
│       │   ├── Choice.java
│       │   ├── Emotion.java
│       │   ├── GameState.java
│       │   ├── Line.java
│       │   ├── Node.java
│       │   ├── NodeChoice.java
│       │   ├── NodeConditional.java
│       │   ├── NodeDialogue.java
│       │   ├── NodeLoader.java
│       │   ├── Position.java
│       │   ├── VNEngine.java
│       │   └── YamlParser.java
│       │
│       └── resources/
│           ├── intro.yaml
│           ├── morning.yaml
│           ├── conversation.yaml
│           ├── first_choice.yaml
│           ├── honesty_path.yaml
│           ├── lie_path.yaml
│           └── aftermath.yaml
│
├── app/
├── build.gradle
├── settings.gradle.kts
└── run.sh
```

## Technologies

* **Java**
* **Gradle**
* **SnakeYAML**
* YAML-based story scripting

## Running the Demo

Clone the repository and run the provided script:

```bash
git clone <repository-url>
cd <repository>
./run.sh
```

Alternatively, the project can be built using Gradle:

```bash
./gradlew build
```

## Current Limitations

This is an experimental engine rather than a finished visual novel framework.

Some planned or incomplete areas include:

* GUI presentation
* Character sprite rendering
* Background rendering
* Music and sound playback
* More advanced conditional logic
* Save/load functionality
* More expressive scripting
* Better YAML validation and error reporting
* Additional node types
* Story editor tooling

## Future Direction

The main goal of the project is to evolve the engine toward a reusable visual novel framework.

One planned direction is separating the **story engine** from the **presentation layer**, allowing the same underlying engine to be used by different interfaces.

For example:

```text
                 ┌───────────────┐
                 │  VN Engine    │
                 │               │
                 │ Game State    │
                 │ Nodes         │
                 │ Choices       │
                 │ Story Parser  │
                 └───────┬───────┘
                         │
             ┌───────────┴───────────┐
             ▼                       ▼
       ┌───────────┐           ┌───────────┐
       │    CLI    │           │    GUI    │
       └───────────┘           └───────────┘
```

The GUI version can therefore build upon the same underlying narrative and state-management system rather than duplicating the engine.

## Why I Built This

This project started as a personal project I started as a joke between my friends, and it bloomed into a much more meaningful project it originally started out as. 

The main things I wanted to explore were:

* Object-oriented game architecture
* State management
* Graph-based narrative systems
* Data-driven game design
* External scripting formats
* Branching dialogue
* Separating game logic from presentation

It is also an ongoing experiment in figuring out how much functionality can be represented through a relatively small and extensible engine.

## Status

**Personal project — WORKING**

## Possible Future Plans

These are things that could further boost my learning and this projects usability as a basic visual novel:

* custom dsl for more custom story telling
* save/load system
* debug console

The architecture and APIs are expected to change as new features are added.
