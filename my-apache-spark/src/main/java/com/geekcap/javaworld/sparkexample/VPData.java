package com.geekcap.javaworld.sparkexample;

import java.io.Serializable;

public class VPData implements Serializable {

    private String pnrLocator;
    private String pcc;
    private String remarkLine;

    public VPData(String pnrLocator, String pcc, String remarkLine) {
        this.pnrLocator = pnrLocator;
        this.pcc = pcc;
        this.remarkLine = remarkLine;
    }

    public String getPnrLocator() {
        return pnrLocator;
    }

    public void setPnrLocator(String pnrLocator) {
        this.pnrLocator = pnrLocator;
    }

    public String getPcc() {
        return pcc;
    }

    public void setPcc(String pcc) {
        this.pcc = pcc;
    }

    public String getRemarkLine() {
        return remarkLine;
    }

    public void setRemarkLine(String remarkLine) {
        this.remarkLine = remarkLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VPData vpData = (VPData) o;

        if (pnrLocator != null ? !pnrLocator.equals(vpData.pnrLocator) : vpData.pnrLocator != null) return false;
        if (pcc != null ? !pcc.equals(vpData.pcc) : vpData.pcc != null) return false;
        return remarkLine != null ? remarkLine.equals(vpData.remarkLine) : vpData.remarkLine == null;

    }

    @Override
    public int hashCode() {
        int result = pnrLocator != null ? pnrLocator.hashCode() : 0;
        result = 31 * result + (pcc != null ? pcc.hashCode() : 0);
        result = 31 * result + (remarkLine != null ? remarkLine.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return pnrLocator + ',' + pcc + ',' + remarkLine;
    }
}
