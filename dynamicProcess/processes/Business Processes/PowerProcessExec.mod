[Ivy]
178FE432108C321F 9.2.0 #module
>Proto >Proto Collection #zClass
Pc0 PowerProcessExec Big #zClass
Pc0 B #cInfo
Pc0 #process
Pc0 @AnnotationInP-0n ai ai #zField
Pc0 @TextInP .type .type #zField
Pc0 @TextInP .processKind .processKind #zField
Pc0 @TextInP .xml .xml #zField
Pc0 @TextInP .responsibility .responsibility #zField
Pc0 @StartRequest f0 '' #zField
Pc0 @UserTask f1 '' #zField
Pc0 @TkArc f2 '' #zField
Pc0 @UserTask f3 '' #zField
Pc0 @TkArc f4 '' #zField
Pc0 @UserTask f5 '' #zField
Pc0 @TkArc f6 '' #zField
Pc0 @EndTask f7 '' #zField
Pc0 @PushWFArc f8 '' #zField
>Proto Pc0 Pc0 PowerProcessExec #zField
Pc0 f0 outLink startExec.ivp #txt
Pc0 f0 requestEnabled true #txt
Pc0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Component</name>
    </language>
</elementInfo>
' #txt
Pc0 f0 35 85 30 30 -36 17 #rect
Pc0 f1 startMethod () #txt
Pc0 f1 requestActionDecl '<> param;' #txt
Pc0 f1 responseMappingAction 'out=in;
' #txt
Pc0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>a1</name>
    </language>
</elementInfo>
' #txt
Pc0 f1 194 78 112 44 -8 -7 #rect
Pc0 f2 65 100 194 100 #arcP
Pc0 f3 startMethod () #txt
Pc0 f3 requestActionDecl '<> param;' #txt
Pc0 f3 responseMappingAction 'out=in;
' #txt
Pc0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>b</name>
    </language>
</elementInfo>
' #txt
Pc0 f3 394 78 112 44 -4 -7 #rect
Pc0 f4 306 100 394 100 #arcP
Pc0 f5 startMethod () #txt
Pc0 f5 requestActionDecl '<> param;' #txt
Pc0 f5 responseMappingAction 'out=in;
' #txt
Pc0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>c</name>
    </language>
</elementInfo>
' #txt
Pc0 f5 594 78 112 44 -3 -7 #rect
Pc0 f6 506 100 594 100 #arcP
Pc0 f7 835 85 30 30 0 15 #rect
Pc0 f8 706 100 835 100 #arcP
>Proto Pc0 .type dynamic.process.Data #txt
>Proto Pc0 .processKind NORMAL #txt
>Proto Pc0 0 0 32 24 18 0 #rect
>Proto Pc0 @|BIcon #fIcon
Pc0 f0 mainOut f2 tail #connect
Pc0 f2 head f1 in #connect
Pc0 f1 out f4 tail #connect
Pc0 f4 head f3 in #connect
Pc0 f3 out f6 tail #connect
Pc0 f6 head f5 in #connect
Pc0 f5 out f8 tail #connect
Pc0 f8 head f7 mainIn #connect
