package ru.max.stepick.algorithms.lineSegment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LinesAndPoints
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        //amount of lines
        System.out.print("enter amount of lines:");
        int n = sc.nextInt();

        List<LineSegment> lines = new ArrayList<>(5);

        System.out.print("enter start point and end point of your line:");
        //filling up and creation of lines
        for(int i = 0; i < n; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            lines.add(new LineSegment(l, r));
        }

        doAlgorithm(lines);
    }

    private static void doAlgorithm(List<LineSegment> lines)
    {
        LineExecutor lineExecutor = new LineExecutor();
        List<Integer> commonPointsArray = new ArrayList<>();

        //1 - sort lines in array
        lineExecutor.sort(lines);

        //sorted lines
        System.out.println("Sorted lines");
        for(int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }
        System.out.println();

        for(int i = 0; i < lines.size(); i++) {
            if(lines.get(i).isUsed()){
                continue;
            }

            //2 - find entering lines in left line
            List<LineSegment> groupLines = lineExecutor.findGroupLines(lines, i);

            //3 - find left common point for all lines which has (isUsed == true) do it in LineExecutor class
            commonPointsArray.add(lineExecutor.findCommonPoint(groupLines, lines));
        }

        //RESULT
        System.out.println("Amount of points:" + commonPointsArray.size());

        for(Integer l: commonPointsArray) {
            System.out.println("points: " + l);
        }
    }
}

class LineExecutor
{
    public LineSegment findLeftLine(List<LineSegment> lines, int index)
    {
        if(lines.isEmpty()){
            try{
                throw new Exception("List is empty");
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        LineSegment leftLine = lines.get(index);

        for(LineSegment line: lines) {
            if(line.isUsed() || leftLine.isUsed()){
                continue;
            }

            if(leftLine.getL() > line.getL()){
                leftLine = line;
            }
        }

        leftLine.setUsed(true);

        return leftLine;
    }

    public List<LineSegment> findGroupLines(List<LineSegment> lines, int index)
    {
        List<LineSegment> linesArr = new ArrayList<>();

        //need to add 1 left line because we won't do it in the future loops
        linesArr.add(lines.get(index));
        lines.get(index).setUsed(true);

        for(LineSegment line: lines) {
            if(line.isUsed()){
                continue;
            }

            boolean shouldToAdd = false;

            for(LineSegment lineUsed: linesArr) {
                if(detectPointInRange(line.getL(), lineUsed)){
                    shouldToAdd = true;
                } else{
                    shouldToAdd = false;
                    break;
                }
            }

            if(shouldToAdd){
                linesArr.add(line);
                line.setUsed(true);
            }
        }

        return linesArr;
    }

    public int findCommonPoint(List<LineSegment> groupLines, List<LineSegment> linesAll)
    {
        //get first element from subgroup of lines entering to each other
        int commonPoint = groupLines.get(0).getR();

        for(LineSegment line: linesAll) {
            while(! groupLines.contains(line) && detectPointInRange(commonPoint, line)) {
                commonPoint += 1;
            }
        }

        return commonPoint;
    }

    public void sort(List<LineSegment> lines)
    {
        for(int i = lines.size() - 1; i > 0; i--) {
            for(int j = 0; j < i; j++) {
            /*Сравниваем элементы попарно,
              если они имеют неправильный порядок,
              то меняем местами*/
                if(lines.get(j).getL() > lines.get(j + 1).getL() || lines.get(j).getL() == lines.get(j + 1).getL() && lines.get(j).getLength() > lines.get(i + 1).getLength()){
                    LineSegment tmp = lines.get(j);
                    lines.set(j, lines.get(j + 1));
                    lines.set(j + 1, tmp);
                }
            }
        }
    }

    private boolean detectPointInRange(Integer commonPoint, LineSegment line)
    {
        return commonPoint >= line.getL() && commonPoint <= line.getR() ? true : false;
    }
}

//
class LineSegment
{
    private int l;
    private int r;
    private int length;
    private boolean isUsed;

    public LineSegment(int l, int r)
    {
        this.l = l;
        this.r = r;
        this.length = this.r - this.l;
    }

    public int getL( )
    {
        return l;
    }

    public boolean isUsed( )
    {
        return isUsed;
    }

    public void setUsed(boolean used)
    {
        isUsed = used;
    }

    public int getR( )
    {
        return r;
    }

    public int getLength( )
    {
        return length;
    }

    @Override
    public String toString( )
    {
        return "l=" + l + ", r=" + r;
    }
}

