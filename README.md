
<!-- PROJECT LOGO -->
<br />
<p align="center">
  <h1 align="center">Circle intersection algorithm using a sweep line</h1>

  <p align="center">
    Given a text file with one each line the coordinate of the center and the radius of each circle, find all the intersections of the given circles and write their coordinates to a text file.
    <br />
    <br />
  </p>
</p>


<!-- ABOUT THE PROJECT -->
## About The Project

The program reads the text file with the given circles and stores them. Then, it is possible to call one of three methods to calculate the intersections of the circles:
* The first algorithm is a brute force approach which calculates for each circle if it has intersections with every other circle. The time complexity is `O(n²)` 
- <img src="https://latex.codecogs.com/gif.latex?O_t=\text { Onset event at time bin } t " /> 
* The second algorithm is a naive sweep line algorithm which uses a priority queue to store the currently active circles and checks for intersections between the newly added circle and all the other circles in the queue whenever a new circle is added. The worst-case time complexity is `O(n²)`.
* The third algorithm is an efficient sweep line algorithm which uses a binary search tree to store the currently active circles and checks for intersections between a circle and its neighbouring circles in the tree whenever the circle is added to the tree, whenever an intersection occurs and whenever a circle is removed from the tree remove

### Built With
* [Java](https://www.java.com/)
* [JavaFX](https://openjfx.io/)


<!-- GETTING STARTED -->
## Getting Started

TODO: ...

<!-- LICENSE -->
## License

Distributed under the MIT License.



Program that finds the intersections between all the given cirles given in the in.txt file using three algorithms.
TODO: add description of algorithms.
TODO: fix last algorithm.
