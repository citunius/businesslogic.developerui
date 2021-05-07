define("ace/mode/rivescript_highlight_rules",["require","exports","module","ace/lib/oop","ace/mode/text_highlight_rules"], function(require, exports, module) {
"use strict";

var oop = require("../lib/oop");
var TextHighlightRules = require("./text_highlight_rules").TextHighlightRules;

var RivescriptHighlightRules = function() {
    // regexp must not have capturing parentheses. Use (?:) instead.
    // regexps are ordered -> the first match is used

    this.$rules = {
        start: [{
            include: "#comments"
        }, {
            include: "#triggers"
        }, {
            include: "#replies"
        }, {
            include: "#defines"
        }, {
            include: "#labels"
        }, {
            include: "#conditions"
        }],
        "#alternates": [{
            token: [
                "keyword.control",
                "string.unquoted",
                "keyword.control"
            ],
            regex: /(\s\()([A-Za-z0-9\s\-]*)(\|)/,
            push: [{
                token: "keyword.control",
                regex: /\)/,
                next: "pop"
            }, {
                include: "#characters"
            }, {
                defaultToken: "meta.alternates"
            }]
        }],
        "#characters": [{
            token: "keyword.control",
            regex: /\,|\;|=|\|/
        }],
        "#comments": [{
            token: "comment.line.double-slash",
            regex: /\/\/.*$/
        }, {
            token: "comment.block",
            regex: /\/\*/,
            push: [{
                token: "comment.block",
                regex: /\*\//,
                next: "pop"
            }, {
                defaultToken: "comment.block"
            }]
        }],
        "#conditions": [{
            token: [
                "keyword.other",
                "string.unquoted",
                "keyword.other",
                "string.unquoted",
                "keyword.other"
            ],
            regex: /^(\s*\*\s)(.+)(\s+(?:eq|ne|==|<>|<=|>=|<|>)\s+)(.+)(=>)/,
            push: [{
                token: "meta.condition",
                regex: /$/,
                next: "pop"
            }, {
                include: "#tags"
            }, {
                include: "#comments"
            }, {
                defaultToken: "meta.condition"
            }]
        }],
        "#constants": [{
            token: "constant.numeric",
            regex: /\d+/
        }, {
            token: "constant.character",
            regex: /[A-Za-z0-9_]*/
        }, {
            token: "string.quoted.single",
            regex: /\'.*\'/
        }],
        "#defines": [{
            token: "keyword.other",
            regex: /^\s*\!\s(?:version|global|var|sub|person|array)/,
            push: [{
                token: "meta.define",
                regex: /$/,
                next: "pop"
            }, {
                include: "#comments"
            }, {
                include: "#characters"
            }, {
                defaultToken: "meta.define"
            }]
        }],
        "#labels": [{
            token: "variable.parameter",
            regex: /\s*[<>]\s+begin/
        }, {
            token: ["variable.parameter", "string.unquoted"],
            regex: /(\s*[<>]\s+topic\s*)([A-Za-z0-9\\-_ ]*)/
        }, {
            token: [
                "variable.parameter",
                "string.unquoted",
                "variable.parameter"
            ],
            regex: /(\s*[<>]\s+object\s+)((?:A-Za-z0-9\-_]+)?)((?:\s+[A-Za-z0-9\-_]+)?)/
        }],
        "#optionals": [{
            token: [
                "keyword.control",
                "string.unquoted",
                "keyword.control"
            ],
            regex: /(\s\[)([A-Za-z0-9\s\-]*)(\])/
        }],
        "#replies": [{
            token: "keyword.control",
            regex: /^\-/,
            push: [{
                token: "meta.reply",
                regex: /$/,
                next: "pop"
            }, {
                include: "#comments"
            }, {
                include: "#tags"
            }, {
                defaultToken: "meta.reply"
            }]
        }],
        "#tags": [{
            token: [
                "variable.parameter",
                "string.quoted",
                "variable.parameter",
                "variable.parameter"
            ],
            regex: /(<|\{)(\/?[A-Za-z0-9_]+)(.*?)(>|\})/
        }, {
            token: "variable.parameter",
            regex: /<|>/
        }],
        "#triggers": [{
            token: "keyword.control",
            regex: /^\+/,
            push: [{
                token: "meta.trigger",
                regex: /$/,
                next: "pop"
            }, {
                include: "#comments"
            }, {
                include: "#alternates"
            }, {
                include: "#optionals"
            }, {
                defaultToken: "meta.trigger"
            }]
        }]
    }
    
    this.normalizeRules();
};

RivescriptHighlightRules.metaData = {
    fileTypes: ["rive"],
    name: "Rivescript",
    scopeName: "text.rive"
}


oop.inherits(RivescriptHighlightRules, TextHighlightRules);

exports.RivescriptHighlightRules = RivescriptHighlightRules;
});

define("ace/mode/rivescript",["require","exports","module","ace/lib/oop","ace/mode/text","ace/mode/rivescript_highlight_rules","ace/mode/matching_brace_outdent","ace/mode/behaviour/cstyle","ace/mode/folding/cstyle","ace/worker/worker_client"], function(require, exports, module) {
"use strict";

var oop = require("../lib/oop");
var TextMode = require("./text").Mode;
var RivescriptHighlightRules = require("./rivescript_highlight_rules").RivescriptHighlightRules;
// TODO: pick appropriate fold mode
//var FoldMode = require("./folding/cstyle").FoldMode;

var Mode = function() {
    this.HighlightRules = RivescriptHighlightRules;
    //this.foldingRules = new FoldMode();
};
oop.inherits(Mode, TextMode);

(function() {
    // this.lineCommentStart = ""/\\*"";
    // this.blockComment = {start: ""/*"", end: ""*/""};
    // Extra logic goes here.
    this.$id = "ace/mode/rivescript"
}).call(Mode.prototype);

exports.Mode = Mode;
});