
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
* The second algorithm is a naive sweep line algorithm which uses a priority queue to store the currently active circles. The worst-case time complexity is `O(n²)`.
* TODO: ...

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
