[Ivy]
178FE13FF6576DC6 9.2.0 #module
>Proto >Proto Collection #zClass
Pr0 PatternConverter Big #zClass
Pr0 B #cInfo
Pr0 #process
Pr0 @AnnotationInP-0n ai ai #zField
Pr0 @TextInP .type .type #zField
Pr0 @TextInP .processKind .processKind #zField
Pr0 @TextInP .xml .xml #zField
Pr0 @TextInP .responsibility .responsibility #zField
Pr0 @StartRequest f0 '' #zField
Pr0 @EndTask f1 '' #zField
Pr0 @GridStep f3 '' #zField
Pr0 @PushWFArc f4 '' #zField
Pr0 @PushWFArc f2 '' #zField
>Proto Pr0 Pr0 PatternConverter #zField
Pr0 f0 outLink start.ivp #txt
Pr0 f0 inParamDecl '<> param;' #txt
Pr0 f0 requestEnabled true #txt
Pr0 f0 triggerEnabled false #txt
Pr0 f0 callSignature start() #txt
Pr0 f0 caseData businessCase.attach=true #txt
Pr0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Pr0 f0 @C|.responsibility Everybody #txt
Pr0 f0 81 49 30 30 -25 17 #rect
Pr0 f1 337 49 30 30 0 15 #rect
Pr0 f3 actionTable 'out=in;
' #txt
Pr0 f3 actionCode 'import com.axon.ivy.template.PatternConverter;
PatternConverter.convert();' #txt
Pr0 f3 168 42 112 44 0 -7 #rect
Pr0 f4 111 64 168 64 #arcP
Pr0 f2 280 64 337 64 #arcP
>Proto Pr0 .type dynamic.process.Data #txt
>Proto Pr0 .processKind NORMAL #txt
>Proto Pr0 0 0 32 24 18 0 #rect
>Proto Pr0 @|BIcon #fIcon
Pr0 f0 mainOut f4 tail #connect
Pr0 f4 head f3 mainIn #connect
Pr0 f3 mainOut f2 tail #connect
Pr0 f2 head f1 mainIn #connect
