import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Algorithm3 {
    public CircleIntersectionsProgram circleIntersectionsProgram;
    public ArrayList<Point> allIntersections;
    public PriorityQueue<EventPoint> allEventPoints = new PriorityQueue<>();
    public ArrayList<Pair<CustomCirle, CustomCirle>> alreadyChecktSegments;

    public Algorithm3(CircleIntersectionsProgram circleIntersectionsProgram){
        this.circleIntersectionsProgram = circleIntersectionsProgram;
        this.allIntersections = new ArrayList<>();
        this.alreadyChecktSegments = new ArrayList<>();
    }

    public List<Point> doAlgorithm3() {
        this.allEventPoints = circleIntersectionsProgram.getAllStartAndEndCirclePointsPQ();
        BST<CircleSegment, Double> activeCircles = new BST<CircleSegment, Double>();

        int size = this.allEventPoints.size();
        for(int i = 0; i < size; i++){
            EventPoint eventPoint = this.allEventPoints.poll();
            // System.out.println("allintersections: " + this.allIntersections);
            // System.out.println("tree: " + activeCircles);
            //activeCircles
            if(eventPoint.isStartOfSegment) {
                // System.out.println("start-event");
                // add circle to tree
                CircleSegment circleSegment1 = new CircleSegment(true, eventPoint.circle);
                CircleSegment circleSegment2 = new CircleSegment(false, eventPoint.circle);
                activeCircles.put(circleSegment1, eventPoint.y);
                activeCircles.put(circleSegment2, eventPoint.y);
                //check neighbours
                List<EventPoint> foundIntersectionsCircleSegment1 = checkForIntersectionsWithNeighbours(activeCircles, circleSegment1);
                List<EventPoint> foundIntersectionsCircleSegment2 = checkForIntersectionsWithNeighbours(activeCircles, circleSegment2);
                List<EventPoint> allIntersectionsFound =  Stream.concat(foundIntersectionsCircleSegment1.stream(), foundIntersectionsCircleSegment2.stream()).collect(Collectors.toList());

                // add found intersection to event points
                this.allEventPoints.addAll(allIntersectionsFound);
                // add found intersections to all intersections list
                this.allIntersections.addAll(allIntersectionsFound);
            }
            else if(eventPoint.isIntersection){
                // System.out.println("intersection-event: c1: " + eventPoint.circleSegmentIntersection1 + " c2: " + eventPoint.circleSegmentIntersection2);
                // getNode circleSegment1
                BST<CircleSegment, Double>.Node circleSegment1 = activeCircles.getNode(eventPoint.circleSegmentIntersection1);
                // getNode circleSegment2
                BST<CircleSegment, Double>.Node circleSegment2 = activeCircles.getNode(eventPoint.circleSegmentIntersection2);

                //swap values of circles
                BST<CircleSegment, Double>.Node temp = activeCircles.new Node(circleSegment1.key, circleSegment1.val, circleSegment1.size);
                circleSegment1.key = circleSegment2.key;
                circleSegment2.key = temp.key;

                //check neighbours
                List<EventPoint> foundIntersectionsCircle1 = checkForIntersectionsWithNeighbours(activeCircles, circleSegment1.key);
                List<EventPoint> foundIntersectionsCircle2 = checkForIntersectionsWithNeighbours(activeCircles, circleSegment2.key);
                List<EventPoint> allIntersectionsFound =  Stream.concat(foundIntersectionsCircle1.stream(), foundIntersectionsCircle2.stream()).collect(Collectors.toList());

                // add found intersection to event points
                this.allEventPoints.addAll(allIntersectionsFound);
                // add found intersections to all intersections list
                this.allIntersections.addAll(allIntersectionsFound);
            }
            else{
                //System.out.println("end-event");
                CircleSegment circleSegment1 = new CircleSegment(true, eventPoint.circle);
                CircleSegment circleSegment2 = new CircleSegment(false, eventPoint.circle);

                int rankCircleSegmentUpper = activeCircles.rank(circleSegment1);
                CircleSegment neighbour1 = null;
                CircleSegment neighbour2 = null;
                if(rankCircleSegmentUpper-2 > -1) neighbour1 = activeCircles.select(rankCircleSegmentUpper-2);
                if(rankCircleSegmentUpper+1 < activeCircles.size()) neighbour2 = activeCircles.select(rankCircleSegmentUpper+1);

                // remove circle from tree
                activeCircles.delete(circleSegment1);
                activeCircles.delete(circleSegment2);
                //check neighbours
                List<EventPoint> foundIntersectionsCircle1 = new ArrayList<>(); List<EventPoint> foundIntersectionsCircle2 = new ArrayList<>();
                if(neighbour1 != null) foundIntersectionsCircle1 = checkForIntersectionsWithNeighbours(activeCircles, neighbour1);
                if(neighbour2 != null) foundIntersectionsCircle2 = checkForIntersectionsWithNeighbours(activeCircles, neighbour2);
                List<EventPoint> allIntersectionsFound =  Stream.concat(foundIntersectionsCircle1.stream(), foundIntersectionsCircle2.stream()).collect(Collectors.toList());

                // add found intersection to event points
                this.allEventPoints.addAll(allIntersectionsFound);
                // add found intersections to all intersections list
                this.allIntersections.addAll(allIntersectionsFound);
            }
        }
        return this.allIntersections;
    }

    public List<EventPoint> checkForIntersectionsWithNeighbours(BST<CircleSegment, Double> activeCircles, CircleSegment circleSegment){
        int rankCircle = activeCircles.rank(circleSegment);
        CircleSegment neighbour1 = null; CircleSegment neighbour2 = null;
        if(rankCircle-1 > -1) neighbour1 = activeCircles.select(rankCircle-1);
        if(rankCircle+1 < activeCircles.size()) neighbour2 = activeCircles.select(rankCircle+1);

        // calculate intersections
        List<EventPoint> intersectionsCircleAndNeighbour1 = new ArrayList<>(); List<EventPoint> intersectionsCircleAndNeighbour2 = new ArrayList<>();
        if(neighbour1 != null) intersectionsCircleAndNeighbour1 = circleIntersectionsProgram.calculateIntersection(circleSegment, neighbour1);
        if(neighbour2 != null) intersectionsCircleAndNeighbour2 = circleIntersectionsProgram.calculateIntersection(circleSegment, neighbour2);
        List<EventPoint> allIntersectionsFound =  Stream.concat(intersectionsCircleAndNeighbour1.stream(), intersectionsCircleAndNeighbour2.stream()).collect(Collectors.toList());

        // only return new points found
        List<EventPoint> allIntersectionsFoundNoDups = new ArrayList<>();
        for (Point intersection : allIntersectionsFound) {
            if (this.allIntersections.contains(intersection)) continue;
            allIntersectionsFoundNoDups.add((EventPoint) intersection);
        }

        return allIntersectionsFoundNoDups;
    }

}