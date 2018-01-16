package trials;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommonValidatorTest {

    public static void main(String[] args) {
        CommonValidatorTest obj = new CommonValidatorTest();
        obj.run();
    }

    public void run() {
        List<PtpTrade> ptpTradeList = new ArrayList<>(); //Supplied by incoming exchange
        List<PtpTrade> newTrades = new ArrayList<>();
        List<PtpTrade> stagedTrades = new ArrayList<>();

        Set<String> newAllocationIdSet = new HashSet<>();
        Set<String> stagedAllocationIdSet = new HashSet<>();
        Set<String> stagedAllocationIdVersionSet = new HashSet<>();

        //Handle duplicates within the batch
        ptpTradeList.forEach(t -> {
            if("N".equalsIgnoreCase(t.getLastAction())) {
                boolean isUnique = newAllocationIdSet.add(t.getSourceId());
                if(!isUnique)
                    t.setPreErrCode("DUP_ALLOC_ID");
                newTrades.add(t);
            } else {
                stagedAllocationIdSet.add(t.getSourceId());
                boolean isUnique = stagedAllocationIdVersionSet.add(t.getSourceId() + t.getCurrentVersion());
                if(!isUnique)
                    t.setPreErrCode("DUP_ALLOC_ID_VERSION_COMB");
                stagedTrades.add(t);
            }
        });

        List<String> allUniqueAllocationIds = new ArrayList<>();
        allUniqueAllocationIds.addAll(newAllocationIdSet);
        allUniqueAllocationIds.addAll(stagedAllocationIdSet);
        List<String> allMatchingAllocationIdsFromTB = new ArrayList<>(); //hit DB ("select p.sourceId from PtpTrade where p.sourceId in :ids").setParameter("ids", allUniqueAllocationIds);

        //Handle duplicates from DB for NEW trades
        if(!newTrades.isEmpty()) {
            newTrades.forEach(t -> {
                if(!"DUP_ALLOC_ID".equalsIgnoreCase(t.getPreErrCode())) {
                    if(allMatchingAllocationIdsFromTB.contains(t.getSourceId()))
                        t.setPreErrCode("DUP_ALLOC_ID");
                }
            });
        }

        if(!stagedTrades.isEmpty()) {
            //Handle Staged Trades attempting action on missing Trades
            //Handle duplicates from DB for Staged trades

            List<String> matchingAllocationIdVersionFromTBS = new ArrayList<>(); //hit DB ("select CONCAT(s.sourceId, s.receivedVersion) from StagedPtpTrade s where s.sourceId in :ids").setParameter("ids", stagedAllocationIdSet);

            stagedTrades.forEach(t -> {
                if(!allMatchingAllocationIdsFromTB.contains(t.getSourceId()))
                    t.setPreErrCode("MISSING_NEW_TRD");

                if(!"DUP_ALLOC_ID_VERSION_COMB".equalsIgnoreCase(t.getPreErrCode()) && !"MISSING_NEW_TRD".equalsIgnoreCase(t.getPreErrCode())) {
                    if(matchingAllocationIdVersionFromTBS.contains(t.getSourceId() + t.getCurrentVersion()))
                        t.setPreErrCode("DUP_ALLOC_ID_VERSION_COMB");
                }
            });
        }
    }
}

class PtpTrade {
    String sourceId;
    String currentVersion;
    String lastAction;
    Timestamp addDate;
    String preErrCode;

    public PtpTrade() {
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getLastAction() {
        return lastAction;
    }

    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
    }

    public Timestamp getAddDate() {
        return addDate;
    }

    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }

    public String getPreErrCode() {
        return preErrCode;
    }

    public void setPreErrCode(String preErrCode) {
        this.preErrCode = preErrCode;
    }
}

class StagedPtpTrade {

    String sourceId;
    String receivedVersion;
    String lastAction;

    public StagedPtpTrade() {
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getReceivedVersion() {
        return receivedVersion;
    }

    public void setReceivedVersion(String receivedVersion) {
        this.receivedVersion = receivedVersion;
    }

    public String getLastAction() {
        return lastAction;
    }

    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
    }
}
