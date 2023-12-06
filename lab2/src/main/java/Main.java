public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(3,3);
        //double[] values = {0,1,2,-1,0,3,-5,-6,0};
        //double[] values1_9 = {1,2,3,4,5,6,7,8,9};
        //double[] values3 = {2,3,2,1,2,-3,3,4,1};
        //double[] values4 = {1,2,3,2,4,9,3,8,12};
        double[] values2x3 = {1,2,3,4,5,6,7,8,9};
        matrix.fill(values2x3);
        System.out.println(matrix);

        System.out.println("--------------------------------------------------\n");

        //ImmutableMatrix sum = new ImmutableMatrix(matrix.multiply(mat_copy));
        //System.out.println(sum);

        System.out.println("--------------------------------------------------\n");

        Matrix matrix1 = new Matrix(matrix);
        System.out.println(matrix1.toTriUpper());
        System.out.println("--------------------------------------------------\n");
        System.out.println(matrix1.toTriLower());
    }
}
