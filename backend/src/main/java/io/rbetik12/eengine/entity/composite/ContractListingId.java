package io.rbetik12.eengine.entity.composite;

import java.io.Serializable;

public class ContractListingId implements Serializable {
    private int contractId;
    private int listing;

    public ContractListingId() {
    }

    public ContractListingId(int contractId, int listing) {
        this.contractId = contractId;
        this.listing = listing;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getListing() {
        return listing;
    }

    public void setListing(int listing) {
        this.listing = listing;
    }
}
