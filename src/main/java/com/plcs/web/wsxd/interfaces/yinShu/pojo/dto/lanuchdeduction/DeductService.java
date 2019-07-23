package com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.lanuchdeduction;

import com.alibaba.fastjson.annotation.JSONField;
import com.plcs.web.wsxd.interfaces.yinShu.pojo.dto.common.YinShuServiceHeaderDTO;


public class DeductService {

    @JSONField(name = "SERVICE_HEADER")
    private YinShuServiceHeaderDTO header;

    @JSONField(name = "SERVICE_BODY")
    private DeductBody body;

    public YinShuServiceHeaderDTO getHeader() {
        return header;
    }

    public void setHeader(YinShuServiceHeaderDTO header) {
        this.header = header;
    }

    public DeductBody getBody() {
        return body;
    }

    public void setBody(DeductBody body) {
        this.body = body;
    }
}
