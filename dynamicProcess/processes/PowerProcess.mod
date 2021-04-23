[Ivy]
178FE13903DC1BDA 9.2.0 #module
>Proto >Proto Collection #zClass
Ps0 PowerProcess Big #zClass
Ps0 B #cInfo
Ps0 #process
Ct0 Component Big #zClass
Ct0 B #cInfo
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @StartRequest f0 '' #zField
Ps0 @EndTask f1 '' #zField
Ps0 Ct0 S10 'Sub 1' #zField
Ps0 @PushWFArc f3 '' #zField
Ps0 @PushWFArc f2 '' #zField
>Proto Ps0 Ps0 PowerProcess #zField
Ct0 @AnnotationInP-0n ai ai #zField
Ct0 @TextInP .type .type #zField
Ct0 @TextInP .processKind .processKind #zField
Ct0 @TextInP .xml .xml #zField
Ct0 @TextInP .responsibility .responsibility #zField
Ct0 @PushTrueWFInG-01 g0 '' #zField
Ct0 @PushTrueWFOutG-01 g1 '' #zField
Ct0 @PushWFArc f0 '' #zField
Ct0 @UserTask f1 '' #zField
Ct0 @UserTask f2 '' #zField
Ct0 @UserTask f3 '' #zField
>Proto Ct0 Ct0 Component #zField
Ps0 f0 outLink start.ivp #txt
Ps0 f0 inParamDecl '<> param;' #txt
Ps0 f0 requestEnabled true #txt
Ps0 f0 triggerEnabled false #txt
Ps0 f0 callSignature start() #txt
Ps0 f0 caseData businessCase.attach=true #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <desc>TEMPLATE</desc>
    </language>
</elementInfo>
' #txt
Ps0 f0 @C|.responsibility Everybody #txt
Ps0 f0 81 49 30 30 -25 17 #rect
Ps0 f1 561 49 30 30 0 15 #rect
Ps0 S10 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>multiUser</name>
    </language>
</elementInfo>
' #txt
Ps0 S10 148 72 424 576 -29 -7 #rect
Ps0 S10 1 #transp
Ps0 f3 106 75 148 122 #arcP
Ps0 f2 570 72 567 76 #arcP
>Proto Ps0 .type dynamic.process.Data #txt
>Proto Ps0 .processKind NORMAL #txt
>Proto Ps0 0 0 32 24 18 0 #rect
>Proto Ps0 @|BIcon #fIcon
Ct0 g0 51 243 26 26 0 5 #rect
Ct0 g1 563 243 26 26 0 5 #rect
Ct0 f0 77 256 563 256 #arcP
Ct0 f1 responseMappingAction 'out=in;
' #txt
Ct0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>a1</name>
    </language>
</elementInfo>
' #txt
Ct0 f1 72 72 432 48 -8 -7 #rect
Ct0 f2 responseMappingAction 'out=in;
' #txt
Ct0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>b</name>
    </language>
</elementInfo>
' #txt
Ct0 f2 264 154 112 44 -4 -7 #rect
Ct0 f3 responseMappingAction 'out=in;
' #txt
Ct0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>c</name>
    </language>
</elementInfo>
' #txt
Ct0 f3 232 314 112 44 -3 -7 #rect
>Proto Ct0 0 0 32 24 18 0 #rect
>Proto Ct0 @|BIcon #fIcon
Ps0 f0 mainOut f3 tail #connect
Ps0 f3 head S10 g0 #connect
Ps0 S10 g1 f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ct0 g0 m f0 tail #connect
Ct0 f0 head g1 m #connect
Ct0 0 0 640 512 0 #ivRect
