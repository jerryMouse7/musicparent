package com.liu.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.alipay.api.AlipayConstants.NOTIFY_URL;

public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);


    public static void main(String[] args) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                "2019091567394657",
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCROQyBK8tewgKWTkXnUv+hhvRXiqQ+Vds3TYMweoXTKrJfX7ibqmT4v/SIDg+Q3glRiY1jOCU/UyLbYXbNRtairSq4DD9QrEZYy0YBWa+LTcYJED5cX6GpQTbc+bNZpe+AagNGWGfn/EXboVdq8cm6GVQCE5AxSzP6pvmkyyxEiijZrI/2MLdBJvIOyUyfidh1fclIFvZXlBcmQoV9rtZfTnKOlI3rsAzvwzIASsyy6d0r6xuOTpyc/FeLhfTaTS3wXP3d/hRmj3Ue3nSuqmNTrN2UDlBks7K8HOZZPLp6xIcv6dih5ZAFh8JMyyQ2pDTEjiD47PiZdziF/yXih4hPAgMBAAECggEAB60ERC+UaEXI7Tjl6PwyFs+TSq1BUSoolBu8u2s+d5LiKrhcgZ3YCG3c9vQHTpjt1Sl4F654CYjTZtUX+nGsFp0gjOnYzuLMNRVJdfjPloVd2kzJfwhu+ws/Yx6vpCVpK1hvI+C4XUKwTpOQk06EYxnDnvh5vqFC9hStTVCP3m96n0vzVFvkA9JFY11AfGhCMFRZYkMAMk3DEsI5+yqJz/gh35rdE+dycjpNcUca6lmL2e5UL6aItRxXiTIjBhe1eOmCNR8h73A3eGOAom80XI8y4qMXNbX5/DvpCRyAd3gDzedv2mrXxhQQUqlIUAmBjwJZangOpGckSgpNnG+OQQKBgQDLw84/uN4dXMuYk61kALdQr5Am7oo8uOAHzriNwDHki2dNbjeGjcpKsHG7wSHvEzDCKZpb+R8/bocghf7ez3JKJF/HjC2u1OiwY53IdROYAIebnMeaGhUf/QwoDOUfphAlEFCFixQLpaCPpjuj4BDDR1cnTnW9KKyRJ1D4YQ+R5QKBgQC2c2MlDqxwhxHyyF0//7pk2PSaLOJFWRb8+bvfGfnqjDiZBpc2wXregJfXiLn/N1aBCKhjgEqIqaaIyt91NmdWcsoi6RGAR5bi1tIJynNo50eW920BUVSxU54/Z7hy7OjCpZE2ksa2j4I3eYJGTPthfO0KdI41MuB6WXag4zreIwKBgEEIxKo34cRzWpPfBmc1h0ycTCDw33QT+YTkaKjtX7828wiWleg/FwKRoCsxsBfzAwvlZVvXfi6rozsxKTCJ+0tIor04s1fqoj2x/aj+HPvVp3J2LoqzqmxkQl4CB5r7UInT1C9bPVGeUkmm38AlApShD2L/Orrd4X4Ok1xC9ko1AoGBAIr6D/6NU9sPeCOIbkeuw0vIW2IJaH5Mo48lp+8EuXLpRr+B3rNt4+sS5xbd5lg6zeDSOHo6ogvBtsIiTt7ZBxQ5sjGveY5OeefmnO2qUgkJi2W6qUTEsA+3UyaXGlAomBCUz61TVpx/akBZhVuJjj6vS1yYdi6dx5JLDWr2S8QjAoGBAKZc3KgpnUkZLRjNK8DpsefmkKHEkuDI4GzNFHUCYoKWC2qRubvMGEvPNyRWsoEzJII3wtw55cvszKoDbtvv57YlNlFuGOroZs6yq7ohQlEUBFZEo3EYG2H3qyAtM2DLNXhTYki1Oxvp9UCL38fSBwmk8WckzkmKDwnk8OBQu6Ev",
                "json",
                "UTF-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkold8zSMytWoiEUcBCo+vI6oQE8LP/j6z472jK95XK5TRNgFiihUdVbfcUO5O9KcaZeTaf+kZMOdq7Kl6gTVoNSZ1vtmJLowLHH8Fa+4nY9+t5U7NhTEA5f0T8RMiL2F9BjfotZCzKpaRKMw6SrZROHPdhZTNizm9NtV2/W2kG6+uHwf6LM+qaMdATCsb7JT8OInu066h5Xuiz8PZSmiY/3Ht1m3gmfIa9rBLW5jJfeSpFxbN+sJ8Kfo6lsuTawTeBWZsMpImI8WtlKeY4niaKnvoqy2SIcdgv3Y5vVoUSJ1EVFKUzvJ6SJbWgYavnxSoMCcMpuUpQwc6X6tdBp4iQIDAQAB",
                "RSA2");
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
////        request.setBizContent("{"+
////                "\"out_trade_no\":\"123456798\""+","+
////                "\"totle_amount\":\"5.00\""+","+
////                "\"subject\":\"Iphone6 16G\"," +
////                "      \"goods_detail\":[{" +
////                "        \"goods_id\":\"apple-01\""+"}],"
////                +"}");
        request.setReturnUrl("http://localhost:8080/alipay/alipayNotifyNotice.action");
        request.setNotifyUrl("http://localhost:8080/alipay/alipayNotifyNotice.action");
        request.setBizContent("{" +
                "\"out_trade_no\":\"20150320010101001\"," +
                "\"seller_id\":\"2088102146225135\"," +
                "\"total_amount\":88.88," +
                "\"discountable_amount\":8.88," +
                "\"undiscountable_amount\":80," +
                "\"buyer_logon_id\":\"15901825620\"," +
                "\"subject\":\"Iphone6 16G\"," +
                "      \"goods_detail\":[{" +
                "        \"goods_id\":\"apple-01\"," +
                "\"alipay_goods_id\":\"20010001\"," +
                "\"goods_name\":\"ipad\"," +
                "\"quantity\":1," +
                "\"price\":2000," +
                "\"goods_category\":\"34543238\"," +
                "\"categories_tree\":\"124868003|126232002|126252004\"," +
                "\"body\":\"特价手机\"," +
                "\"show_url\":\"http://www.alipay.com/xxx.jpg\"" +
                "        }]," +
                "\"body\":\"Iphone6 16G\"," +
                "\"product_code\":\"FACE_TO_FACE_PAYMENT\"," +
                "\"operator_id\":\"yx_001\"," +
                "\"store_id\":\"NJ_001\"," +
                "\"disable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
                "\"enable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
                "\"terminal_id\":\"NJ_T_001\"," +
                "\"extend_params\":{" +
                "\"sys_service_provider_id\":\"2088511833207846\"," +
                "\"hb_fq_num\":\"3\"," +
                "\"hb_fq_seller_percent\":\"100\"," +
                "\"industry_reflux_info\":\"{\\\\\\\"scene_code\\\\\\\":\\\\\\\"metro_tradeorder\\\\\\\",\\\\\\\"channel\\\\\\\":\\\\\\\"xxxx\\\\\\\",\\\\\\\"scene_data\\\\\\\":{\\\\\\\"asset_name\\\\\\\":\\\\\\\"ALIPAY\\\\\\\"}}\"," +
                "\"card_type\":\"S0JP0000\"" +
                "    }," +
                "\"timeout_express\":\"90m\"," +
                "\"royalty_info\":{" +
                "\"royalty_type\":\"ROYALTY\"," +
                "        \"royalty_detail_infos\":[{" +
                "          \"serial_no\":1," +
                "\"trans_in_type\":\"userId\"," +
                "\"batch_no\":\"123\"," +
                "\"out_relation_id\":\"20131124001\"," +
                "\"trans_out_type\":\"userId\"," +
                "\"trans_out\":\"2088101126765726\"," +
                "\"trans_in\":\"2088101126708402\"," +
                "\"amount\":0.1," +
                "\"desc\":\"分账测试1\"," +
                "\"amount_percentage\":\"100\"" +
                "          }]" +
                "    }," +
                "\"settle_info\":{" +
                "        \"settle_detail_infos\":[{" +
                "          \"trans_in_type\":\"cardAliasNo\"," +
                "\"trans_in\":\"A0001\"," +
                "\"summary_dimension\":\"A0001\"," +
                "\"settle_entity_id\":\"2088xxxxx;ST_0001\"," +
                "\"settle_entity_type\":\"SecondMerchant、Store\"," +
                "\"amount\":0.1" +
                "          }]" +
                "    }," +
                "\"sub_merchant\":{" +
                "\"merchant_id\":\"19023454\"," +
                "\"merchant_type\":\"alipay: 支付宝分配的间连商户编号, merchant: 商户端的间连商户编号\"" +
                "    }," +
                "\"alipay_store_id\":\"2016052600077000000015640104\"," +
                "\"merchant_order_no\":\"20161008001\"," +
                "\"ext_user_info\":{" +
                "\"name\":\"李明\"," +
                "\"mobile\":\"16587658765\"," +
                "\"cert_type\":\"IDENTITY_CARD\"," +
                "\"cert_no\":\"362334768769238881\"," +
                "\"min_age\":\"18\"," +
                "\"fix_buyer\":\"F\"," +
                "\"need_check_info\":\"F\"" +
                "    }," +
                "\"business_params\":{" +
                "\"campus_card\":\"0000306634\"," +
                "\"card_type\":\"T0HK0000\"," +
                "\"actual_order_time\":\"2019-05-14 09:18:55\"" +
                "    }," +
                "\"qr_code_timeout_express\":\"90m\"" +
                "  }");
        logger.info(request.getBizContent());
        String response = alipayClient.pageExecute(request).getBody();
        System.out.println(response);

    }
}
