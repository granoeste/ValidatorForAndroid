/*
 * Copyright (C) 2012 granoeste@gmail.com (https://github.com/granoeste)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.granoeste.validator;

public class JapanesePhoneValidator extends MaskValidator {

    /** 電話番号の正規表現 */
    // 国内プレフィックス:0
    // 市外局番  : 1 - 4桁 <--+-- 合計5桁
    // 市内局番  : 1 - 4桁 <--+
    // 加入者番号: 4桁
    // 0A-BCDE-1234
    // 0AB-CDE-1234
    // 0ABC-DE-1234
    // 0ABCD-E-1234

    // 携帯電話
    // 090-1234-1234

    // その他特殊
    // 0124-123-123
    // 123456-1234-1234

    public static final String PATTERN_TELNO = "^0[0-9]{4}-[0-9]{1}-[0-9]{3,4}$|^0[0-9]{3}-[0-9]{2}-[0-9]{3,4}$|^0[0-9]{2}-[0-9]{3}-[0-9]{3,4}$|^0[0-9]{1}-[0-9]{4}-[0-9]{3,4}$|^0[0-9]{2}-[0-9]{4}-[0-9]{4}$|^0120-[0-9]{3}-[0-9]{3}$|^\\d{1,6}-\\d{1,4}-\\d{1,4}$";

    public JapanesePhoneValidator(final String errorMessage) {
        super(PATTERN_TELNO, errorMessage);
    }

}
