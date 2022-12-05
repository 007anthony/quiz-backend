package ch.bbw.ap.quizbackend.model.request;

public class Paging {

    private int offset;
    private Integer rows;

    public Paging() {
        this.offset = 0;
        this.rows = null;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
