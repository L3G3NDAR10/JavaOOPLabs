import java.util.Arrays;

public class Matrix {
    private double[][] data;
    private int rows;
    private int columns;

    public Matrix() {
        this(0, 0);
    }

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new double[rows][columns];
    }

    public int getRowsN(){
        return data.length;
    }

    public int getColumnsN(){
        return data[0].length;
    }

    public Matrix(Matrix matrix) {
        this.rows = matrix.rows;
        this.columns = matrix.columns;
        this.data = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.data[i][j] = matrix.data[i][j];
            }
        }
    }

    public void fill(double[] values) {
        int length = values.length;
        if (length != rows * columns) {
            throw new IllegalArgumentException("The size of the value array is incorrect.");
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                data[i][j] = values[i * columns + j];
            }
        }
    }

    public void fill(double value) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                data[i][j] = value;
            }
        }
    }

    public void fill() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                data[i][j] = Math.random() * (10 - (-10)) + (-10);
            }
        }
    }

    public void setElement(int row, int column, double value) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            throw new IllegalArgumentException("Indices outside the matrix.");
        }

        data[row][column] = value;
    }

    public void setRow(int row, double[] values) {
        if (row < 0 || row >= rows || values.length != columns) {
            throw new IllegalArgumentException("Incorrect dimensions.");
        }

        for (int i = 0; i < columns; i++) {
            data[row][i] = values[i];
        }
    }

    public void setColumn(int column, double[] values) {
        if (column < 0 || column >= columns || values.length != rows) {
            throw new IllegalArgumentException("Incorrect dimensions.");
        }

        for (int i = 0; i < rows; i++) {
            data[i][column] = values[i];
        }
    }

    public double getElement(int row, int column) {
        if (row < 0 || row >= rows) {
            throw new IndexOutOfBoundsException("Invalid row number.");
        }

        if (column < 0 || column >= columns) {
            throw new IndexOutOfBoundsException("Invalid column number.");
        }

        return data[row][column];
    }

    public String getRow(int row) {
        if (row < 0 || row >= rows) {
            throw new IndexOutOfBoundsException("Invalid row number.");
        }

        return Arrays.toString(data[row]);
    }

    public String getColumn(int column) {
        if (column < 0 || column >= columns) {
            throw new IndexOutOfBoundsException("Invalid column number.");
        }

        double[] columnArray = new double[rows];
        for (int i = 0; i < rows; i++) {
            columnArray[i] = data[i][column];
        }

    return Arrays.toString(columnArray);
    }

    public String getSize() {
        return Arrays.toString(new int[]{rows, columns});
    }

    public Matrix add(Matrix otherMatrix) {
        if (rows != otherMatrix.rows || columns != otherMatrix.columns) {
            throw new IllegalArgumentException("Matrices do not have the same dimensionality.");
        }

        Matrix result = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.data[i][j] = data[i][j] + otherMatrix.data[i][j];
            }
        }

        return result;
    }

    public Matrix multiply(double scalar) {
        Matrix result = new Matrix(this);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.data[i][j] = this.data[i][j] * scalar;
            }
        }

        return result;
    }

    public Matrix multiply(Matrix otherMatrix) {
        if (columns != otherMatrix.rows) {
            throw new IllegalArgumentException("Impossible to multiply this matrices.");
        }

        Matrix result = new Matrix(rows, otherMatrix.columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < otherMatrix.columns; j++) {
                double sum = 0.0;
                for (int k = 0; k < columns; k++) {
                    sum += data[i][k] * otherMatrix.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }

        return result;
    }

    public Matrix transpose() {
        Matrix result = new Matrix(columns, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.data[j][i] = data[i][j];
            }
        }

        return result;
    }

    public static Matrix diagonal(double[] values) {
        int n = values.length;
        Matrix matrix = new Matrix(n, n);

        for (int i = 0; i < n; i++) {
            matrix.data[i][i] = values[i];
        }

        return matrix;
    }

    public static Matrix unit(int n){
        if(n<0){
            throw new IllegalArgumentException("Order of matrix can't be negative:");
        }

        Matrix matrix = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            matrix.data[i][i] = 1;
        }

        return matrix;
    }

    public static Matrix randomRow(int n) {
        Matrix result = new Matrix(1, n);
        result.fill();

        return result;
    }

    public static Matrix randomColumn(int n) {
        Matrix result = new Matrix(n, 1);
        result.fill();

        return result;
    }

    public void setData(double[][] data) {
        if (data.length != rows || data[0].length != columns) {
            throw new IllegalArgumentException("Input dimensions must match matrix dimensions");
        }
        this.data = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(data[i], 0, this.data[i], 0, columns);
        }
    }

    public Matrix toTriUpper(){
        int minLenght;
        if (this.columns < this.rows) {
            minLenght = this.columns;
        } else {
            minLenght = this.rows;
        }
        Matrix result = new Matrix(this);
        for (int i = 0; i < minLenght; i++) {
            boolean matrix_swaped = false;
            for (int j = i; j < this.rows; j++) {
                if (matrix_swaped) {
                } else {
                    if (result.data[j][i] != 0) {
                        double[] temp = result.data[i];
                        result.data[i] = result.data[j];
                        result.data[j] = temp;
                        matrix_swaped = true;
                    }
                }
            }
            double first_d = result.data[i][i];
            for (int j = 1 + i; j < result.rows; j++) {
                double other_d = result.data[j][i];
                if (first_d != 0) {
                    double alpha = other_d / first_d;
                    for (int k = 0; k < result.columns; k++) {
                        result.data[j][k] = result.data[j][k] - alpha*result.data[i][k];
                    }
                }
            }
        }
        return result;
    }

    public Matrix toTriLower(){
        int LastDigitOfTriangul;
        if (this.columns < this.rows) {
            LastDigitOfTriangul = 0;
        } else {
            LastDigitOfTriangul = this.columns - this.rows;
        }
        Matrix result = new Matrix(this);
        int count_backing = 0;
        for (int i = result.columns - 1; i > LastDigitOfTriangul - 1; i--) {
            count_backing++;
            int row_pivot = result.rows - count_backing;

            Boolean matrix_swaped = false;
            for (int j = row_pivot; j > -1; j--) {
                if(matrix_swaped){

                } else {
                    if (result.data[j][i] != 0) {
                        double[] temp = result.data[row_pivot];
                        result.data[row_pivot] = result.data[j];
                        result.data[j] = temp;
                        matrix_swaped = true;
                    }
                }
            }

            double first_d = result.data[row_pivot][i];

            for (int j = row_pivot - 1; j > -1 ; j--) {
                double other_d = result.data[j][i];
                if(first_d != 0) {
                    double alpha = other_d / first_d;
                    for (int k = 0; k < result.columns; k++) {
                        result.data[j][k] = result.data[j][k] - alpha*result.data[row_pivot][k];
                    }
                }
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Matrix otherMatrix = (Matrix) obj;

        if (rows != otherMatrix.rows || columns != otherMatrix.columns) {
            return false;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (data[i][j] != otherMatrix.data[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + rows;
        hash = 31 * hash + columns;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                hash = 31 * hash + Double.hashCode(data[i][j]);
            }
        }

        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sb.append(data[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
