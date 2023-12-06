import java.util.Arrays;

public final class ImmutableMatrix extends Matrix {

    public ImmutableMatrix() {
        super();
    }

    public ImmutableMatrix(int rows, int columns) {
        super(rows, columns);
    }

    public ImmutableMatrix(Matrix matrix) {
        super(matrix);
    }

    @Override
    public void fill(double[] values) {
        throw new IllegalStateException("Can't change imutable matrix");
    }

    @Override
    public void fill(double value) {
        throw new IllegalStateException("Can't change imutable matrix");
    }

    @Override
    public void fill() {
        throw new IllegalStateException("Can't change imutable matrix");
    }

    @Override
    public void setElement(int row, int column, double value) {
        throw new IllegalStateException("Can't change imutable matrix");
    }

    @Override
    public void setRow(int row, double[] values) {
        throw new IllegalStateException("Can't change imutable matrix");
    }

    @Override
    public void setColumn(int column, double[] values) {
        throw new IllegalStateException("Can't change imutable matrix");
    }

    @Override
    public double getElement(int row, int column) {
        return new Matrix(this).getElement(row, column);
    }

    @Override
    public String getRow(int row) {
        return new Matrix(this).getRow(row);
    }

    @Override
    public String getColumn(int column) {
        return new Matrix(this).getColumn(column);
    }

    @Override
    public String getSize() {
        return new Matrix(this).getSize();
    }

    @Override
    public Matrix add(Matrix otherMatrix) {
        return new Matrix(this).add(otherMatrix);
    }

    @Override
    public Matrix multiply(double scalar) {
        return new Matrix(this).multiply(scalar);
    }

    @Override
    public Matrix multiply(Matrix otherMatrix) {
        return new Matrix(this).multiply(otherMatrix);
    }

    @Override
    public Matrix transpose() {
        return new Matrix(this).transpose();
    }

    public Matrix triangularShapeUpper() {
        return new Matrix(this).toTriUpper();
    }

    public Matrix triangularShapeLower() {
        return new Matrix(this).toTriLower();
    }
}