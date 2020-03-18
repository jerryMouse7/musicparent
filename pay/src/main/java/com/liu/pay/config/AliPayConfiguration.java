package com.liu.pay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliPayConfiguration {

    @Bean
    public AlipayClient alipayClient(){
        return new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                                "2019091567394657",
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCYFXAB8ip7qL5k879V5NIGkJ6HrFiMhe7fPyp+PSrRsvvi5Gka8esle43O8+X7UfdKZ5S6YbzZpzZmgEZOmYmYd/GRhUX1CG5B6bbcIuBzg/lqeF3WsMepfMyy+A8/mPyBa3axvzz4LkPJafp9uqrr0wRHmDyMdk0vvrWL/LtcqZcjkRJssBHiWa8+3yYUdEaSCsMFa/i8Lg7Z9YroKLhGqKFV69b00B+HwryAbFsoHKoagkWZpKoR2MISuAM+gyGQYYog5l6aW+MIehuNGZa8veADKG916BpWKkMUfUjBzRxp8mVNTxhJasCGCl/rrFVsTkzfRKH2089k4K9XCaMJAgMBAAECggEAWU/lmk7z4fED9tcqGzAooxBU+Qoeak/RKyu2MbKMLd/1ipZmnxtI45c+fTXAbqoBFqHjttzlAdDv4pwcLdZ+9pohtDrXDYIo9e5ho7+bnlr3oy4StZzVdFRFBiklE00tpHdOmi3biUfqKSNipml0LM+caMsfa8GhrJNLnG0MPuqvXRxme/paXc2ePCIAKMDIRvN3tN8UhMkfeljqnR7Qs53ICW21hWBd8SI+6It3jFGYm4aURx1WuL97bxpr06l+rdDH6EoQfqsC+jl8NQzWdGMIz5Eb+RTUcsRUiIybGJzEgY9O70h9Bdzm8LrCI9T+oyQMcyLLr92PtJo06xvQAQKBgQD4WXLzXlAypr9RlJt21X04+J1XF1dtj9uqvoYhy0EQEomJ6zujFSnE2KHWYa2AFGqDigUUQINL+JGRzm5gAoa7G8xP5ZuUN0VtVg5IePvwKIFCVGSQsdMjert3+TijNDo1IqLBgHRQwDaWUjQtX14oRhi2SATApDkEmHorXAi9CQKBgQCcxM+uhZyy1Ra0NDrxQg71cUez4nzpR+Nwzv/DiNL/lS9bX+19o6c1zed5s1Ha/eycrQ5wNuWp913sKDtv21lyd1O0VaH2EC99+FAmqLLqc1CVLY0+YaIHH3ybVDK/5gcWCX/BE5yJ1VoMCIYFHR3L0nsqqt0LltQUHi6y01k2AQKBgQCQAb5IJJ44YLx7n8JuK+lKyc7im2RU4aAs6AF7QxLdMQKT+NVCAZmQzVU6rLUgmbYRxEyhq672tJIkjCEwhwZ9JOdaRPfsAAjAF7B8kbr1TWd2gN03kgWugxKFxPv0lupRuV3K+qN3151PmScsUCqXC950hFpNImz1nzzoO5vNkQKBgC3buLDhruP1R43xVWirnTqZJ5NsZoPLHrIqK1EknixblJ3TIuLE063+jH7iQMihzOxMB1Q2Ctq8XMIvRqvC65h7ZYX0IsubEnl0AOY+JRBGKUVqLM4Zu08AG/BIIgxWmq4Mm018kicTvSKV/7ZRPR0ZdiQWFVFFjsZPyfNPteIBAoGBAO1rqVwV1Q/ZneAZP9IEazmGdW0uxIkRqpI0QoEe5A3hjb/f1t58Id+tgpxRSkPwtR+gcpQqzOGueAT2AG8Ly6x+wqn29OPCsE2NYI9h6qM+8BHBblryo7qO++kTQ2OshfR8gW9bS/UKhpA9s4TjckCF9rDZmxAH7gMEcCGEJ5DQ",
                "json",
                "UTF-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkold8zSMytWoiEUcBCo+vI6oQE8LP/j6z472jK95XK5TRNgFiihUdVbfcUO5O9KcaZeTaf+kZMOdq7Kl6gTVoNSZ1vtmJLowLHH8Fa+4nY9+t5U7NhTEA5f0T8RMiL2F9BjfotZCzKpaRKMw6SrZROHPdhZTNizm9NtV2/W2kG6+uHwf6LM+qaMdATCsb7JT8OInu066h5Xuiz8PZSmiY/3Ht1m3gmfIa9rBLW5jJfeSpFxbN+sJ8Kfo6lsuTawTeBWZsMpImI8WtlKeY4niaKnvoqy2SIcdgv3Y5vVoUSJ1EVFKUzvJ6SJbWgYavnxSoMCcMpuUpQwc6X6tdBp4iQIDAQAB",
                "RSA2");

    }
}
