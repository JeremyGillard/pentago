# Pentago <img src="./media/img/icon.png" width="40" height="40"> 

<img src="./media/img/capture.png" width="500">

## Description

Pentago is a strategy game for two players. As illustrated in Figure 1, it consists of a 6x6 box tray, itself divided into four 3x3 quadrants. This game is supplied with 18 white and 18 black balls, at the beginning of the game the player associated with the white color is determined. This player will start the game. Each turn, a player places a colored ball on a free square on the board. Then the player decides to turn the quadrant on which he has placed his billed' a quarter turn to the right or left. a player wins by aligning five of his balls in a vertical or horizontal row (before or after the rotation of the quadrant). If each of the 36 squares is occupied without forming a row of five balls of the same color, the game is drawn.

See more rules informations [here](PentagoRulesStrategy.pdf).

NB: (small rule change) even if two players have lined up 5 balls after turning a quadrant, only the current player will be designated as the winner.

## Model Structure

There are **4** main parts to this project:
+ The part to manage the console view (be.jeremygillard.pentago.**console**)
+ The part to manage the graphical view (be.jeremygillard.pentago.**gui**)
+ An utility part (be.jeremygillard.pentago.**util**) 
+ And finally the most important part, the model of our game (be.jeremygillard.pentago.**model**).

```java
public class Author {
    public static void main(String[] args) {
        System.out.println("Pentago v1.0 finished. Have Fun !");
    }
}
```

## Additional Informations

Java build tool used: Ant

Jdk version: 1.8

### Code (additional information - comments)

Only small inconsistency between the console view and the graphical view. I had to put the isOver check in the ball placement and quadrant rotation methods for the mistletoe while for the console view, the isOver method is used for the controller loop.

For a more pleasant gaming experience, it is possible on most operating systems to uncomment line 20: initSurroundingMusic().

### Other
The commits structure are highly inspired by the Angular "Commit Message Conventions".

Briefly, the pattern look like this:

\<type\>(\<scope\>): \<subject\>
\<description\>
\<footer\>

NB: (Customization) In my projects, I will try at best to refer to parts of Model Structure for the header <scope>.

[For more information](https://gist.github.com/stephenparish/9941e89d80e2bc58a153#file-commit-md)

In the future, I would also like to draw inspiration from the advice given in [this article](https://bulldogjob.com/news/449-how-to-write-a-good-readme-for-your-github-project) in my future projects.

## Licensing

With confirmation from my laboratory supervisor I would like to post this project on my personal github with the license below (to be confirmed).

**Question: Can a real game under private license be licensed to copy-left in the "digital world"?**

This app is Licensed under the GNU General Public License v3.0. See [LICENSE](LICENSE) for the full license text.

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

