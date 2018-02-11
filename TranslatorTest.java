package trials;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TranslatorTest {

    public static void main(String[] args) {
        TranslatorTest obj = new TranslatorTest();
        obj.translate();
    }

    private List<Flow> translate(/*String refId*/) {
        List<QueryResult> queryResults = new ArrayList<>(); //TODO Get from dao
        List<Flow> flows = new ArrayList<>();
        for(QueryResult row : queryResults) {
            Flow flow = null;

            if("C".equalsIgnoreCase(row.getLastAction()) && !Objects.isNull(row.getAmendsLinkId()))
                continue;
            else if("A".equalsIgnoreCase(row.getLastAction())) {
                flow = new Flow();
                Workflow workflow = new Workflow();
                Stream stream = new Stream();
                /*
                flow.setOrigin(row.getSource());
                flow.set
                 */
            } else {
                flow = new Flow();
                Workflow workflow = new Workflow();
                Stream stream = new Stream();
                /*
                flow.setOrigin(row.getSource());
                flow.set
                 */
            }
            if(!Objects.isNull(flow))
                flows.add(flow);
        }
        return flows;
    }

}

class Flow {}

class Workflow {}

class Stream {}

class QueryResult {
    private String refId;
    private String source;
    private String sourceId;
    private String lastAction;
    private String currentVersion;
    private String amendsLinkId;
    private String jmsMsgId;
    private String msgFromSrc;
    private String msgFromSrcTime;
    private String legType;
    private String compress_enabled;
    private String compress_id;
    private String tag17;
    private String tradeLegFixSeq;
    private String msgToBrr;
    private String msgToBrrTime;
    private String msgFromBrr;
    private String msgFromBrrTime;
    private String msgToSrc;
    private String msgToSrcTime;
    private String tag17Rep;

    public QueryResult() {
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getLastAction() {
        return lastAction;
    }

    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getAmendsLinkId() {
        return amendsLinkId;
    }

    public void setAmendsLinkId(String amendsLinkId) {
        this.amendsLinkId = amendsLinkId;
    }

    public String getJmsMsgId() {
        return jmsMsgId;
    }

    public void setJmsMsgId(String jmsMsgId) {
        this.jmsMsgId = jmsMsgId;
    }

    public String getMsgFromSrc() {
        return msgFromSrc;
    }

    public void setMsgFromSrc(String msgFromSrc) {
        this.msgFromSrc = msgFromSrc;
    }

    public String getMsgFromSrcTime() {
        return msgFromSrcTime;
    }

    public void setMsgFromSrcTime(String msgFromSrcTime) {
        this.msgFromSrcTime = msgFromSrcTime;
    }

    public String getLegType() {
        return legType;
    }

    public void setLegType(String legType) {
        this.legType = legType;
    }

    public String getCompress_enabled() {
        return compress_enabled;
    }

    public void setCompress_enabled(String compress_enabled) {
        this.compress_enabled = compress_enabled;
    }

    public String getCompress_id() {
        return compress_id;
    }

    public void setCompress_id(String compress_id) {
        this.compress_id = compress_id;
    }

    public String getTag17() {
        return tag17;
    }

    public void setTag17(String tag17) {
        this.tag17 = tag17;
    }

    public String getTradeLegFixSeq() {
        return tradeLegFixSeq;
    }

    public void setTradeLegFixSeq(String tradeLegFixSeq) {
        this.tradeLegFixSeq = tradeLegFixSeq;
    }

    public String getMsgToBrr() {
        return msgToBrr;
    }

    public void setMsgToBrr(String msgToBrr) {
        this.msgToBrr = msgToBrr;
    }

    public String getMsgToBrrTime() {
        return msgToBrrTime;
    }

    public void setMsgToBrrTime(String msgToBrrTime) {
        this.msgToBrrTime = msgToBrrTime;
    }

    public String getMsgFromBrr() {
        return msgFromBrr;
    }

    public void setMsgFromBrr(String msgFromBrr) {
        this.msgFromBrr = msgFromBrr;
    }

    public String getMsgFromBrrTime() {
        return msgFromBrrTime;
    }

    public void setMsgFromBrrTime(String msgFromBrrTime) {
        this.msgFromBrrTime = msgFromBrrTime;
    }

    public String getMsgToSrc() {
        return msgToSrc;
    }

    public void setMsgToSrc(String msgToSrc) {
        this.msgToSrc = msgToSrc;
    }

    public String getMsgToSrcTime() {
        return msgToSrcTime;
    }

    public void setMsgToSrcTime(String msgToSrcTime) {
        this.msgToSrcTime = msgToSrcTime;
    }

    public String getTag17Rep() {
        return tag17Rep;
    }

    public void setTag17Rep(String tag17Rep) {
        this.tag17Rep = tag17Rep;
    }
}
