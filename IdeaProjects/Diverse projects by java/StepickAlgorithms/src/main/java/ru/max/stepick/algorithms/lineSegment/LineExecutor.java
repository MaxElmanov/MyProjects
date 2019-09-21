//package ru.max.stepick.algorithms.lineSegment;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class LineExecutor
//{
//    public LineSegment findLeftLine(List<LineSegment> lines, int index)
//    {
//        if(lines.size() == 0){
//            try{
//                throw new Exception("List is empty");
//            } catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//
//        LineSegment leftLine = lines.get(index);
//
//        for(LineSegment line: lines) {
//            if(line.isUsed() || leftLine.isUsed()){
//                continue;
//            }
//
//            if(leftLine.getL() > line.getL()){
//                leftLine = line;
//            }
//        }
//
//        leftLine.setUsed(true);
//
//        return leftLine;
//    }
//
//    public List<LineSegment> findGroupLines(List<LineSegment> lines, int index)
//    {
//        List<LineSegment> linesArr = new ArrayList<>();
//
//        //need to add 1 left line because we won't do it in the future loops
//        linesArr.add(lines.get(index));
//        lines.get(index).setUsed(true);
//
//        for(LineSegment line: lines) {
//            if(line.isUsed()){
//                continue;
//            }
//
//            boolean shouldToAdd = false;
//
//            for(LineSegment lineUsed: linesArr) {
//                if(detectPointInRange(line.getL(), lineUsed)){
//                    shouldToAdd = true;
//                } else{
//                    shouldToAdd = false;
//                    break;
//                }
//            }
//
//            if(shouldToAdd){
//                linesArr.add(line);
//                line.setUsed(true);
//            }
//        }
//
//        return linesArr;
//    }
//
//    public int findCommonPoint(List<LineSegment> GroupLines, List<LineSegment> linesAll)
//    {
//        //get first element from subgroup of lines entering to each other
//        int commonPoint = GroupLines.get(0).getR();
//
//        for(LineSegment line: linesAll) {
//            while(! GroupLines.contains(line) && detectPointInRange(commonPoint, line)) {
//                commonPoint += 1;
//            }
//        }
//
//        return commonPoint;
//    }
//
//    public void sort(List<LineSegment> lines)
//    {
//        for(int i = lines.size() - 1; i > 0; i--) {
//            for(int j = 0; j < i; j++) {
//            /*Сравниваем элементы попарно,
//              если они имеют неправильный порядок,
//              то меняем местами*/
//                if(lines.get(j).getL() > lines.get(j + 1).getL() || lines.get(j).getL() == lines.get(j + 1).getL() && lines.get(j).getLength() > lines.get(i + 1).getLength()){
//                    LineSegment tmp = lines.get(j);
//                    lines.set(j, lines.get(j + 1));
//                    lines.set(j + 1, tmp);
//                }
//            }
//        }
//    }
//
//    private boolean detectPointInRange(Integer commonPoint, LineSegment line)
//    {
//        return commonPoint >= line.getL() && commonPoint <= line.getR() ? true : false;
//    }
//}
