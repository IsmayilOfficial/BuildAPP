$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("negative_case.feature");
formatter.feature({
  "line": 1,
  "name": "Negative Case",
  "description": "",
  "id": "negative-case",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "querying RentIt\u0027s plant catalog",
  "description": "",
  "id": "negative-case;querying-rentit\u0027s-plant-catalog",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "the customer query with  name \"mini\" and startDate \"23.04.2019\" and endDate \"30.04.2019\"",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "the client calls GET \"\" with details",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the client receives status code of 200",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "the response contains name \"Mini excavator\"",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "mini",
      "offset": 31
    },
    {
      "val": "23.04.2019",
      "offset": 52
    },
    {
      "val": "30.04.2019",
      "offset": 77
    }
  ],
  "location": "positiveCaseStep.theCustomerQueryWithNameAndStartDateAndEndDate(String,String,String)"
});
formatter.result({
  "duration": 505928900,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "",
      "offset": 22
    }
  ],
  "location": "positiveCaseStep.theClientCallsGETWithDetails(String)"
});
formatter.result({
  "duration": 902776500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 35
    }
  ],
  "location": "positiveCaseStep.theClientReceivesStatusCodeOf(int)"
});
formatter.result({
  "duration": 852100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Mini excavator",
      "offset": 28
    }
  ],
  "location": "positiveCaseStep.theResponseContainsNameDescriptionPriceActions(String)"
});
formatter.result({
  "duration": 54100,
  "status": "passed"
});
formatter.scenario({
  "line": 9,
  "name": "selecting one plant for creating a Plant hire request",
  "description": "",
  "id": "negative-case;selecting-one-plant-for-creating-a-plant-hire-request",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 10,
  "name": "the customer selected with Plant Name \"Mini excavator\" and Description \"1.5 Tonne Mini excavator\",Price 200",
  "keyword": "Given "
});
formatter.step({
  "line": 11,
  "name": "After the client calls Post \"\" with details",
  "keyword": "When "
});
formatter.step({
  "line": 12,
  "name": "the client receives status code of 201",
  "keyword": "Then "
});
formatter.step({
  "line": 13,
  "name": "the response contains Plant Name \"Mini excavator\" ,Subtotal 1050",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "Mini excavator",
      "offset": 39
    },
    {
      "val": "1.5 Tonne Mini excavator",
      "offset": 72
    },
    {
      "val": "200",
      "offset": 104
    }
  ],
  "location": "positiveCaseStep.theCustomerSelectedWithPlantNameAndDescriptionPrice(String,String,int)"
});
formatter.result({
  "duration": 219700,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "",
      "offset": 29
    }
  ],
  "location": "positiveCaseStep.AftertheClientCallsGETWithDetails(String)"
});
formatter.result({
  "duration": 7709100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "201",
      "offset": 35
    }
  ],
  "location": "positiveCaseStep.theClientReceivesStatusCodeOf(int)"
});
formatter.result({
  "duration": 70100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Mini excavator",
      "offset": 34
    },
    {
      "val": "1050",
      "offset": 60
    }
  ],
  "location": "positiveCaseStep.theResponseContainsPlantNameSubtotal(String,int)"
});
formatter.result({
  "duration": 116499,
  "status": "passed"
});
formatter.scenario({
  "line": 15,
  "name": "accepting the plant hire request",
  "description": "",
  "id": "negative-case;accepting-the-plant-hire-request",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 16,
  "name": "the work enjineer accept with Actions \"reject\"",
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "After the client click to accept button  POST \"/about\"",
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "the client receives status code of 201",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "reject",
      "offset": 39
    }
  ],
  "location": "positiveCaseStep.theWorkEnjineerAcceptWithActions(String)"
});
formatter.result({
  "duration": 128201,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/about",
      "offset": 47
    }
  ],
  "location": "positiveCaseStep.afterTheClientClickToAcceptButtonPOST(String)"
});
formatter.result({
  "duration": 9552500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "201",
      "offset": 35
    }
  ],
  "location": "positiveCaseStep.theClientReceivesStatusCodeOf(int)"
});
formatter.result({
  "duration": 67000,
  "status": "passed"
});
formatter.uri("positive_case.feature");
formatter.feature({
  "line": 1,
  "name": "Positive Case",
  "description": "",
  "id": "positive-case",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "querying RentIt\u0027s plant catalog",
  "description": "",
  "id": "positive-case;querying-rentit\u0027s-plant-catalog",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "the customer query with  name \"mini\" and startDate \"23.04.2019\" and endDate \"30.04.2019\"",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "the client calls GET \"\" with details",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the client receives status code of 200",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "the response contains name \"Mini excavator\"",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "mini",
      "offset": 31
    },
    {
      "val": "23.04.2019",
      "offset": 52
    },
    {
      "val": "30.04.2019",
      "offset": 77
    }
  ],
  "location": "positiveCaseStep.theCustomerQueryWithNameAndStartDateAndEndDate(String,String,String)"
});
formatter.result({
  "duration": 143500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "",
      "offset": 22
    }
  ],
  "location": "positiveCaseStep.theClientCallsGETWithDetails(String)"
});
formatter.result({
  "duration": 7629000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 35
    }
  ],
  "location": "positiveCaseStep.theClientReceivesStatusCodeOf(int)"
});
formatter.result({
  "duration": 86300,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Mini excavator",
      "offset": 28
    }
  ],
  "location": "positiveCaseStep.theResponseContainsNameDescriptionPriceActions(String)"
});
formatter.result({
  "duration": 43501,
  "status": "passed"
});
formatter.scenario({
  "line": 9,
  "name": "selecting one plant for creating a Plant hire request",
  "description": "",
  "id": "positive-case;selecting-one-plant-for-creating-a-plant-hire-request",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 10,
  "name": "the customer selected with Plant Name \"Mini excavator\" and Description \"1.5 Tonne Mini excavator\",Price 200",
  "keyword": "Given "
});
formatter.step({
  "line": 11,
  "name": "After the client calls Post \"\" with details",
  "keyword": "When "
});
formatter.step({
  "line": 12,
  "name": "the client receives status code of 201",
  "keyword": "Then "
});
formatter.step({
  "line": 13,
  "name": "the response contains Plant Name \"Mini excavator\" ,Subtotal 1050",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "Mini excavator",
      "offset": 39
    },
    {
      "val": "1.5 Tonne Mini excavator",
      "offset": 72
    },
    {
      "val": "200",
      "offset": 104
    }
  ],
  "location": "positiveCaseStep.theCustomerSelectedWithPlantNameAndDescriptionPrice(String,String,int)"
});
formatter.result({
  "duration": 247500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "",
      "offset": 29
    }
  ],
  "location": "positiveCaseStep.AftertheClientCallsGETWithDetails(String)"
});
formatter.result({
  "duration": 6810600,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "201",
      "offset": 35
    }
  ],
  "location": "positiveCaseStep.theClientReceivesStatusCodeOf(int)"
});
formatter.result({
  "duration": 59500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Mini excavator",
      "offset": 34
    },
    {
      "val": "1050",
      "offset": 60
    }
  ],
  "location": "positiveCaseStep.theResponseContainsPlantNameSubtotal(String,int)"
});
formatter.result({
  "duration": 91900,
  "status": "passed"
});
formatter.scenario({
  "line": 15,
  "name": "accepting the plant hire request",
  "description": "",
  "id": "positive-case;accepting-the-plant-hire-request",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 16,
  "name": "the work enjineer accept with Actions \"accept\"",
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "After the client click to accept button  POST \"/about\"",
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "the client receives status code of 201",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "accept",
      "offset": 39
    }
  ],
  "location": "positiveCaseStep.theWorkEnjineerAcceptWithActions(String)"
});
formatter.result({
  "duration": 264000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/about",
      "offset": 47
    }
  ],
  "location": "positiveCaseStep.afterTheClientClickToAcceptButtonPOST(String)"
});
formatter.result({
  "duration": 7753400,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "201",
      "offset": 35
    }
  ],
  "location": "positiveCaseStep.theClientReceivesStatusCodeOf(int)"
});
formatter.result({
  "duration": 64299,
  "status": "passed"
});
formatter.scenario({
  "line": 21,
  "name": "checking the state of the Plant hire request",
  "description": "",
  "id": "positive-case;checking-the-state-of-the-plant-hire-request",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 22,
  "name": "In the info panel customer select notfications  Plant Name \"Mini excavator\" status \"Rejected\"",
  "keyword": "Given "
});
formatter.step({
  "line": 23,
  "name": "Calls GET \"about\" with details",
  "keyword": "When "
});
formatter.step({
  "line": 24,
  "name": "the client receives status code of 200",
  "keyword": "Then "
});
formatter.step({
  "line": 25,
  "name": "the response contains Plant name \"Mini excavator\",status \"Accepted\"",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "Mini excavator",
      "offset": 60
    },
    {
      "val": "Rejected",
      "offset": 84
    }
  ],
  "location": "positiveCaseStep.inTheInfoPanelCustomerSelectNotficationsPlantNameStatus(String,String)"
});
formatter.result({
  "duration": 103701,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "about",
      "offset": 11
    }
  ],
  "location": "positiveCaseStep.callsGETWithDetails(String)"
});
formatter.result({
  "duration": 7694900,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 35
    }
  ],
  "location": "positiveCaseStep.theClientReceivesStatusCodeOf(int)"
});
formatter.result({
  "duration": 1486101,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Mini excavator",
      "offset": 34
    },
    {
      "val": "Accepted",
      "offset": 58
    }
  ],
  "location": "positiveCaseStep.theResponseContainsPlantNameStatus(String,String)"
});
formatter.result({
  "duration": 5743500,
  "error_message": "com.fasterxml.jackson.core.JsonParseException: Unexpected character (\u0027\u003c\u0027 (code 60)): expected a valid value (number, String, array, object, \u0027true\u0027, \u0027false\u0027 or \u0027null\u0027)\n at [Source: (String)\"\u003c!DOCTYPE html\u003e\r\n\u003chtml lang\u003d\"en\"\u003e\r\n  \u003chead\u003e\r\n    \u003cmeta charset\u003d\"utf-8\"\u003e\r\n    \u003cmeta http-equiv\u003d\"X-UA-Compatible\" content\u003d\"IE\u003dedge\"\u003e\r\n    \u003cmeta name\u003d\"viewport\" content\u003d\"width\u003ddevice-width,initial-scale\u003d1.0\"\u003e\r\n    \u003clink rel\u003d\"icon\" href\u003d\"/favicon.ico\"\u003e\r\n    \u003clink rel\u003d\"stylesheet\" href\u003d\"//cdn.materialdesignicons.com/2.5.94/css/materialdesignicons.min.css\"\u003e\r\n    \u003ctitle\u003erentitf\u003c/title\u003e\r\n  \u003clink href\u003d\"/about.js\" rel\u003d\"prefetch\"\u003e\u003clink href\u003d\"/app.js\" rel\u003d\"preload\" as\u003d\"script\"\u003e\u003c/head\u003e\r\n  \u003cbody\u003e\r\n    \u003cnoscri\"[truncated 298 chars]; line: 1, column: 2]\r\n\tat com.fasterxml.jackson.core.JsonParser._constructError(JsonParser.java:1804)\r\n\tat com.fasterxml.jackson.core.base.ParserMinimalBase._reportError(ParserMinimalBase.java:693)\r\n\tat com.fasterxml.jackson.core.base.ParserMinimalBase._reportUnexpectedChar(ParserMinimalBase.java:591)\r\n\tat com.fasterxml.jackson.core.json.ReaderBasedJsonParser._handleOddValue(ReaderBasedJsonParser.java:1902)\r\n\tat com.fasterxml.jackson.core.json.ReaderBasedJsonParser.nextToken(ReaderBasedJsonParser.java:757)\r\n\tat com.fasterxml.jackson.databind.ObjectMapper._initForReading(ObjectMapper.java:4141)\r\n\tat com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:4000)\r\n\tat com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3004)\r\n\tat ee.ut.esi.group4.buildit.bdd.positiveCaseStep.theResponseContainsPlantNameStatus(positiveCaseStep.java:183)\r\n\tat ✽.And the response contains Plant name \"Mini excavator\",status \"Accepted\"(positive_case.feature:25)\r\n",
  "status": "failed"
});
});