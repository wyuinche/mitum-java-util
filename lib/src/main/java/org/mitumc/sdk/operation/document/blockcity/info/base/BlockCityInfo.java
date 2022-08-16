package org.mitumc.sdk.operation.document.blockcity.info.base;

import org.mitumc.sdk.Constant;
import org.mitumc.sdk.operation.document.base.Info;
import org.mitumc.sdk.util.Hint;
import org.mitumc.sdk.util.Util;

public class BlockCityInfo extends Info {
    protected BlockCityInfo(String docType, String documentId) throws Exception {
        super(docType, documentId);
    }

    @Override
    public Hint getIdHint() throws Exception {
        try {
            switch (getDocType()) {
                case Constant.MBC_DOCTYPE_USER_DATA:
                    return Hint.get(Constant.MBC_USER_DOCUMENT_ID);
                case Constant.MBC_DOCTYPE_LAND_DATA:
                    return Hint.get(Constant.MBC_LAND_DOCUMENT_ID);
                case Constant.MBC_DOCTYPE_VOTE_DATA:
                    return Hint.get(Constant.MBC_VOTE_DOCUMENT_ID);
                case Constant.MBC_DOCTYPE_HISTORY_DATA:
                    return Hint.get(Constant.MBC_HISTORY_DOCUMENT_ID);
                default:
                    throw new Exception(
                            Util.errMsg("invalid doctype", Util.getName()));
            }
        } catch (Exception e) {
            throw new Exception(
                    Util.linkErrMsgs(
                            Util.errMsg("failed to create hint from doctype", Util.getName()),
                            e.getMessage()));
        }
    }
}
