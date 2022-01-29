# TicTacToeAI

![Kotlin version](https://img.shields.io/static/v1?label=Kotlin&message=1.6.10&color=orange&style=for-the-badge)

### A simple kotlin TicTacToeAI project using [Minimax](https://en.wikipedia.org/wiki/Minimax) algorithm

## Setup Dependencies

### build.gradle

```groovy
repositories {
    maven {
        url = 'https://repsy.io/mvn/happyandjust/repo'
    }
}

dependencies {
    implementation 'com.happyandjust:TicTacToeAI:1.0.0'
}
```

### build.gradle.kts

```kotlin
repositories {
    maven("https://repsy.io/mvn/happyandjust/repo")
}

dependencies {
    implementation("com.happyandjust:TicTacToeAI:1.0.0")
}
```

## Usage

You need a `java.util.Map` whose size is 9 to use TicTacToeAI<br>
I'll show how to use AI with empty map in Java, Kotlin

### Java

```java
class Main {
    public static void main(String[] args) {
        Map<Pos, TicTacToeMove> map = new HashMap<>();
        for (Pos value : Pos.values()) {
            map.put(value, null); // null = empty
        }

        System.out.println(TicTacToeAI.getBestMove(map, TicTacToeMove.O));
    }
}
```

### Kotlin

```kotlin
fun main() {
    val map: TicTacToe = Pos.values().associateWith { null } // null = empty
    println(map.getBestMove(TicTacToeMove.O))
}
```

Result -> `Center`
