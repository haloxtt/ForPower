/**
 * 装水最多的容器
 */
public class MaxArea {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int prev = 0;//前驱指针
        int next = height.length - 1;//后继指针
        while (prev < next) {
            int area = Math.min(height[prev],height[next]) * (next - prev);//当前面积
            if (area > maxArea)
                maxArea = area;
            if (height[prev] < height[next]) {//将指针指向位置较小的向中间移动，因为移动较大值的话一定会小于等于原来的面积
                prev++;
            } else {
                next--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        String[] arr = "s  aad b m".split(" ");
    }
}
