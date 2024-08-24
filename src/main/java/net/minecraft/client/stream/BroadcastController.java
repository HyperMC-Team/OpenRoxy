package net.minecraft.client.stream;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.stream.IngestServerTester;
import net.minecraft.client.stream.TwitchStream;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ThreadSafeBoundList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.twitch.AuthToken;
import tv.twitch.Core;
import tv.twitch.CoreAPI;
import tv.twitch.ErrorCode;
import tv.twitch.MessageLevel;
import tv.twitch.StandardCoreAPI;
import tv.twitch.broadcast.ArchivingState;
import tv.twitch.broadcast.AudioDeviceType;
import tv.twitch.broadcast.AudioParams;
import tv.twitch.broadcast.ChannelInfo;
import tv.twitch.broadcast.DesktopStreamAPI;
import tv.twitch.broadcast.EncodingCpuUsage;
import tv.twitch.broadcast.FrameBuffer;
import tv.twitch.broadcast.GameInfo;
import tv.twitch.broadcast.GameInfoList;
import tv.twitch.broadcast.IStatCallbacks;
import tv.twitch.broadcast.IStreamCallbacks;
import tv.twitch.broadcast.IngestList;
import tv.twitch.broadcast.IngestServer;
import tv.twitch.broadcast.PixelFormat;
import tv.twitch.broadcast.StartFlags;
import tv.twitch.broadcast.StatType;
import tv.twitch.broadcast.Stream;
import tv.twitch.broadcast.StreamAPI;
import tv.twitch.broadcast.StreamInfo;
import tv.twitch.broadcast.StreamInfoForSetting;
import tv.twitch.broadcast.UserInfo;
import tv.twitch.broadcast.VideoParams;

public class BroadcastController {
    private static final Logger logger = LogManager.getLogger();
    protected final int field_152865_a = 30;
    protected final int field_152866_b = 3;
    private static final ThreadSafeBoundList<String> field_152862_C = new ThreadSafeBoundList<String>(String.class, 50);
    private String lastError = null;
    protected BroadcastListener broadcastListener = null;
    protected String field_152868_d = "";
    protected String field_152869_e = "";
    protected String field_152870_f = "";
    protected boolean field_152871_g = true;
    protected Core streamCore = null;
    protected Stream theStream = null;
    protected List<FrameBuffer> field_152874_j = Lists.newArrayList();
    protected List<FrameBuffer> field_152875_k = Lists.newArrayList();
    protected boolean field_152876_l = false;
    protected boolean field_152877_m = false;
    protected boolean field_152878_n = false;
    protected BroadcastState broadcastState = BroadcastState.Uninitialized;
    protected String field_152880_p = null;
    protected VideoParams videoParamaters = null;
    protected AudioParams audioParamaters = null;
    protected IngestList ingestList = new IngestList(new IngestServer[0]);
    protected IngestServer ingestServ = null;
    protected AuthToken authenticationToken = new AuthToken();
    protected ChannelInfo channelInfo = new ChannelInfo();
    protected UserInfo userInfo = new UserInfo();
    protected StreamInfo streamInfo = new StreamInfo();
    protected ArchivingState archivingState = new ArchivingState();
    protected long field_152890_z = 0L;
    protected IngestServerTester ingestServTester = null;
    private ErrorCode errorCode;
    protected IStreamCallbacks streamCallback = new IStreamCallbacks(){

        public void requestAuthTokenCallback(ErrorCode p_requestAuthTokenCallback_1_, AuthToken p_requestAuthTokenCallback_2_) {
            if (ErrorCode.succeeded((ErrorCode)p_requestAuthTokenCallback_1_)) {
                BroadcastController.this.authenticationToken = p_requestAuthTokenCallback_2_;
                BroadcastController.this.func_152827_a(BroadcastState.Authenticated);
            } else {
                BroadcastController.this.authenticationToken.data = "";
                BroadcastController.this.func_152827_a(BroadcastState.Initialized);
                String s = ErrorCode.getString((ErrorCode)p_requestAuthTokenCallback_1_);
                BroadcastController.this.logError(String.format("RequestAuthTokenDoneCallback got failure: %s", s));
            }
            try {
                if (BroadcastController.this.broadcastListener != null) {
                    BroadcastController.this.broadcastListener.func_152900_a(p_requestAuthTokenCallback_1_, p_requestAuthTokenCallback_2_);
                }
            }
            catch (Exception exception) {
                BroadcastController.this.logError(exception.toString());
            }
        }

        public void loginCallback(ErrorCode p_loginCallback_1_, ChannelInfo p_loginCallback_2_) {
            if (ErrorCode.succeeded((ErrorCode)p_loginCallback_1_)) {
                BroadcastController.this.channelInfo = p_loginCallback_2_;
                BroadcastController.this.func_152827_a(BroadcastState.LoggedIn);
                BroadcastController.this.field_152877_m = true;
            } else {
                BroadcastController.this.func_152827_a(BroadcastState.Initialized);
                BroadcastController.this.field_152877_m = false;
                String s = ErrorCode.getString((ErrorCode)p_loginCallback_1_);
                BroadcastController.this.logError(String.format("LoginCallback got failure: %s", s));
            }
            try {
                if (BroadcastController.this.broadcastListener != null) {
                    BroadcastController.this.broadcastListener.func_152897_a(p_loginCallback_1_);
                }
            }
            catch (Exception exception) {
                BroadcastController.this.logError(exception.toString());
            }
        }

        public void getIngestServersCallback(ErrorCode p_getIngestServersCallback_1_, IngestList p_getIngestServersCallback_2_) {
            if (ErrorCode.succeeded((ErrorCode)p_getIngestServersCallback_1_)) {
                BroadcastController.this.ingestList = p_getIngestServersCallback_2_;
                BroadcastController.this.ingestServ = BroadcastController.this.ingestList.getDefaultServer();
                BroadcastController.this.func_152827_a(BroadcastState.ReceivedIngestServers);
                try {
                    if (BroadcastController.this.broadcastListener != null) {
                        BroadcastController.this.broadcastListener.func_152896_a(p_getIngestServersCallback_2_);
                    }
                }
                catch (Exception exception) {
                    BroadcastController.this.logError(exception.toString());
                }
            } else {
                String s = ErrorCode.getString((ErrorCode)p_getIngestServersCallback_1_);
                BroadcastController.this.logError(String.format("IngestListCallback got failure: %s", s));
                BroadcastController.this.func_152827_a(BroadcastState.LoggingIn);
            }
        }

        public void getUserInfoCallback(ErrorCode p_getUserInfoCallback_1_, UserInfo p_getUserInfoCallback_2_) {
            BroadcastController.this.userInfo = p_getUserInfoCallback_2_;
            if (ErrorCode.failed((ErrorCode)p_getUserInfoCallback_1_)) {
                String s = ErrorCode.getString((ErrorCode)p_getUserInfoCallback_1_);
                BroadcastController.this.logError(String.format("UserInfoDoneCallback got failure: %s", s));
            }
        }

        public void getStreamInfoCallback(ErrorCode p_getStreamInfoCallback_1_, StreamInfo p_getStreamInfoCallback_2_) {
            if (ErrorCode.succeeded((ErrorCode)p_getStreamInfoCallback_1_)) {
                BroadcastController.this.streamInfo = p_getStreamInfoCallback_2_;
                try {
                    if (BroadcastController.this.broadcastListener != null) {
                        BroadcastController.this.broadcastListener.func_152894_a(p_getStreamInfoCallback_2_);
                    }
                }
                catch (Exception exception) {
                    BroadcastController.this.logError(exception.toString());
                }
            } else {
                String s = ErrorCode.getString((ErrorCode)p_getStreamInfoCallback_1_);
                BroadcastController.this.logWarning(String.format("StreamInfoDoneCallback got failure: %s", s));
            }
        }

        public void getArchivingStateCallback(ErrorCode p_getArchivingStateCallback_1_, ArchivingState p_getArchivingStateCallback_2_) {
            BroadcastController.this.archivingState = p_getArchivingStateCallback_2_;
            if (ErrorCode.failed((ErrorCode)p_getArchivingStateCallback_1_)) {
                // empty if block
            }
        }

        public void runCommercialCallback(ErrorCode p_runCommercialCallback_1_) {
            if (ErrorCode.failed((ErrorCode)p_runCommercialCallback_1_)) {
                String s = ErrorCode.getString((ErrorCode)p_runCommercialCallback_1_);
                BroadcastController.this.logWarning(String.format("RunCommercialCallback got failure: %s", s));
            }
        }

        public void setStreamInfoCallback(ErrorCode p_setStreamInfoCallback_1_) {
            if (ErrorCode.failed((ErrorCode)p_setStreamInfoCallback_1_)) {
                String s = ErrorCode.getString((ErrorCode)p_setStreamInfoCallback_1_);
                BroadcastController.this.logWarning(String.format("SetStreamInfoCallback got failure: %s", s));
            }
        }

        public void getGameNameListCallback(ErrorCode p_getGameNameListCallback_1_, GameInfoList p_getGameNameListCallback_2_) {
            if (ErrorCode.failed((ErrorCode)p_getGameNameListCallback_1_)) {
                String s = ErrorCode.getString((ErrorCode)p_getGameNameListCallback_1_);
                BroadcastController.this.logError(String.format("GameNameListCallback got failure: %s", s));
            }
            try {
                if (BroadcastController.this.broadcastListener != null) {
                    BroadcastController.this.broadcastListener.func_152898_a(p_getGameNameListCallback_1_, p_getGameNameListCallback_2_ == null ? new GameInfo[]{} : p_getGameNameListCallback_2_.list);
                }
            }
            catch (Exception exception) {
                BroadcastController.this.logError(exception.toString());
            }
        }

        public void bufferUnlockCallback(long p_bufferUnlockCallback_1_) {
            FrameBuffer framebuffer = FrameBuffer.lookupBuffer((long)p_bufferUnlockCallback_1_);
            BroadcastController.this.field_152875_k.add(framebuffer);
        }

        public void startCallback(ErrorCode p_startCallback_1_) {
            if (ErrorCode.succeeded((ErrorCode)p_startCallback_1_)) {
                try {
                    if (BroadcastController.this.broadcastListener != null) {
                        BroadcastController.this.broadcastListener.func_152899_b();
                    }
                }
                catch (Exception exception1) {
                    BroadcastController.this.logError(exception1.toString());
                }
                BroadcastController.this.func_152827_a(BroadcastState.Broadcasting);
            } else {
                BroadcastController.this.videoParamaters = null;
                BroadcastController.this.audioParamaters = null;
                BroadcastController.this.func_152827_a(BroadcastState.ReadyToBroadcast);
                try {
                    if (BroadcastController.this.broadcastListener != null) {
                        BroadcastController.this.broadcastListener.func_152892_c(p_startCallback_1_);
                    }
                }
                catch (Exception exception) {
                    BroadcastController.this.logError(exception.toString());
                }
                String s = ErrorCode.getString((ErrorCode)p_startCallback_1_);
                BroadcastController.this.logError(String.format("startCallback got failure: %s", s));
            }
        }

        public void stopCallback(ErrorCode p_stopCallback_1_) {
            if (ErrorCode.succeeded((ErrorCode)p_stopCallback_1_)) {
                BroadcastController.this.videoParamaters = null;
                BroadcastController.this.audioParamaters = null;
                BroadcastController.this.func_152831_M();
                try {
                    if (BroadcastController.this.broadcastListener != null) {
                        BroadcastController.this.broadcastListener.func_152901_c();
                    }
                }
                catch (Exception exception) {
                    BroadcastController.this.logError(exception.toString());
                }
                if (BroadcastController.this.field_152877_m) {
                    BroadcastController.this.func_152827_a(BroadcastState.ReadyToBroadcast);
                } else {
                    BroadcastController.this.func_152827_a(BroadcastState.Initialized);
                }
            } else {
                BroadcastController.this.func_152827_a(BroadcastState.ReadyToBroadcast);
                String s = ErrorCode.getString((ErrorCode)p_stopCallback_1_);
                BroadcastController.this.logError(String.format("stopCallback got failure: %s", s));
            }
        }

        public void sendActionMetaDataCallback(ErrorCode p_sendActionMetaDataCallback_1_) {
            if (ErrorCode.failed((ErrorCode)p_sendActionMetaDataCallback_1_)) {
                String s = ErrorCode.getString((ErrorCode)p_sendActionMetaDataCallback_1_);
                BroadcastController.this.logError(String.format("sendActionMetaDataCallback got failure: %s", s));
            }
        }

        public void sendStartSpanMetaDataCallback(ErrorCode p_sendStartSpanMetaDataCallback_1_) {
            if (ErrorCode.failed((ErrorCode)p_sendStartSpanMetaDataCallback_1_)) {
                String s = ErrorCode.getString((ErrorCode)p_sendStartSpanMetaDataCallback_1_);
                BroadcastController.this.logError(String.format("sendStartSpanMetaDataCallback got failure: %s", s));
            }
        }

        public void sendEndSpanMetaDataCallback(ErrorCode p_sendEndSpanMetaDataCallback_1_) {
            if (ErrorCode.failed((ErrorCode)p_sendEndSpanMetaDataCallback_1_)) {
                String s = ErrorCode.getString((ErrorCode)p_sendEndSpanMetaDataCallback_1_);
                BroadcastController.this.logError(String.format("sendEndSpanMetaDataCallback got failure: %s", s));
            }
        }
    };
    protected IStatCallbacks field_177949_C = new IStatCallbacks(){

        public void statCallback(StatType p_statCallback_1_, long p_statCallback_2_) {
        }
    };

    public void setBroadcastListener(BroadcastListener broadcastListenerIn) {
        this.broadcastListener = broadcastListenerIn;
    }

    public boolean func_152858_b() {
        return this.field_152876_l;
    }

    public void func_152842_a(String p_152842_1_) {
        this.field_152868_d = p_152842_1_;
    }

    public StreamInfo getStreamInfo() {
        return this.streamInfo;
    }

    public ChannelInfo getChannelInfo() {
        return this.channelInfo;
    }

    public boolean isBroadcasting() {
        return this.broadcastState == BroadcastState.Broadcasting || this.broadcastState == BroadcastState.Paused;
    }

    public boolean isReadyToBroadcast() {
        return this.broadcastState == BroadcastState.ReadyToBroadcast;
    }

    public boolean isIngestTesting() {
        return this.broadcastState == BroadcastState.IngestTesting;
    }

    public boolean isBroadcastPaused() {
        return this.broadcastState == BroadcastState.Paused;
    }

    public boolean func_152849_q() {
        return this.field_152877_m;
    }

    public IngestServer getIngestServer() {
        return this.ingestServ;
    }

    public void setIngestServer(IngestServer ingestServerSet) {
        this.ingestServ = ingestServerSet;
    }

    public IngestList getIngestList() {
        return this.ingestList;
    }

    public void setRecordingDeviceVolume(float volume) {
        this.theStream.setVolume(AudioDeviceType.TTV_RECORDER_DEVICE, volume);
    }

    public void setPlaybackDeviceVolume(float volume) {
        this.theStream.setVolume(AudioDeviceType.TTV_PLAYBACK_DEVICE, volume);
    }

    public IngestServerTester isReady() {
        return this.ingestServTester;
    }

    public long getStreamTime() {
        return this.theStream.getStreamTime();
    }

    protected boolean func_152848_y() {
        return true;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public BroadcastController() {
        this.streamCore = Core.getInstance();
        if (Core.getInstance() == null) {
            this.streamCore = new Core((CoreAPI)new StandardCoreAPI());
        }
        this.theStream = new Stream((StreamAPI)new DesktopStreamAPI());
    }

    protected PixelFormat getPixelFormat() {
        return PixelFormat.TTV_PF_RGBA;
    }

    public boolean func_152817_A() {
        if (this.field_152876_l) {
            return false;
        }
        this.theStream.setStreamCallbacks(this.streamCallback);
        ErrorCode errorcode = this.streamCore.initialize(this.field_152868_d, System.getProperty("java.library.path"));
        if (!this.func_152853_a(errorcode)) {
            this.theStream.setStreamCallbacks((IStreamCallbacks)null);
            this.errorCode = errorcode;
            return false;
        }
        errorcode = this.streamCore.setTraceLevel(MessageLevel.TTV_ML_ERROR);
        if (!this.func_152853_a(errorcode)) {
            this.theStream.setStreamCallbacks((IStreamCallbacks)null);
            this.streamCore.shutdown();
            this.errorCode = errorcode;
            return false;
        }
        if (ErrorCode.succeeded((ErrorCode)errorcode)) {
            this.field_152876_l = true;
            this.func_152827_a(BroadcastState.Initialized);
            return true;
        }
        this.errorCode = errorcode;
        this.streamCore.shutdown();
        return false;
    }

    public boolean func_152851_B() {
        if (!this.field_152876_l) {
            return true;
        }
        if (this.isIngestTesting()) {
            return false;
        }
        this.field_152878_n = true;
        this.func_152845_C();
        this.theStream.setStreamCallbacks((IStreamCallbacks)null);
        this.theStream.setStatCallbacks((IStatCallbacks)null);
        ErrorCode errorcode = this.streamCore.shutdown();
        this.func_152853_a(errorcode);
        this.field_152876_l = false;
        this.field_152878_n = false;
        this.func_152827_a(BroadcastState.Uninitialized);
        return true;
    }

    public void statCallback() {
        if (this.broadcastState != BroadcastState.Uninitialized) {
            if (this.ingestServTester != null) {
                this.ingestServTester.func_153039_l();
            }
            while (this.ingestServTester != null) {
                try {
                    Thread.sleep(200L);
                }
                catch (Exception exception) {
                    this.logError(exception.toString());
                }
                this.func_152821_H();
            }
            this.func_152851_B();
        }
    }

    public boolean func_152818_a(String p_152818_1_, AuthToken p_152818_2_) {
        if (this.isIngestTesting()) {
            return false;
        }
        this.func_152845_C();
        if (p_152818_1_ != null && !p_152818_1_.isEmpty()) {
            if (p_152818_2_ != null && p_152818_2_.data != null && !p_152818_2_.data.isEmpty()) {
                this.field_152880_p = p_152818_1_;
                this.authenticationToken = p_152818_2_;
                if (this.func_152858_b()) {
                    this.func_152827_a(BroadcastState.Authenticated);
                }
                return true;
            }
            this.logError("Auth token must be valid");
            return false;
        }
        this.logError("Username must be valid");
        return false;
    }

    public boolean func_152845_C() {
        if (this.isIngestTesting()) {
            return false;
        }
        if (this.isBroadcasting()) {
            this.theStream.stop(false);
        }
        this.field_152880_p = "";
        this.authenticationToken = new AuthToken();
        if (!this.field_152877_m) {
            return false;
        }
        this.field_152877_m = false;
        if (!this.field_152878_n) {
            try {
                if (this.broadcastListener != null) {
                    this.broadcastListener.func_152895_a();
                }
            }
            catch (Exception exception) {
                this.logError(exception.toString());
            }
        }
        this.func_152827_a(BroadcastState.Initialized);
        return true;
    }

    public boolean func_152828_a(String p_152828_1_, String p_152828_2_, String p_152828_3_) {
        if (!this.field_152877_m) {
            return false;
        }
        if (p_152828_1_ == null || p_152828_1_.equals("")) {
            p_152828_1_ = this.field_152880_p;
        }
        if (p_152828_2_ == null) {
            p_152828_2_ = "";
        }
        if (p_152828_3_ == null) {
            p_152828_3_ = "";
        }
        StreamInfoForSetting streaminfoforsetting = new StreamInfoForSetting();
        streaminfoforsetting.streamTitle = p_152828_3_;
        streaminfoforsetting.gameName = p_152828_2_;
        ErrorCode errorcode = this.theStream.setStreamInfo(this.authenticationToken, p_152828_1_, streaminfoforsetting);
        this.func_152853_a(errorcode);
        return ErrorCode.succeeded((ErrorCode)errorcode);
    }

    public boolean requestCommercial() {
        if (!this.isBroadcasting()) {
            return false;
        }
        ErrorCode errorcode = this.theStream.runCommercial(this.authenticationToken);
        this.func_152853_a(errorcode);
        return ErrorCode.succeeded((ErrorCode)errorcode);
    }

    public VideoParams func_152834_a(int maxKbps, int p_152834_2_, float p_152834_3_, float p_152834_4_) {
        int[] aint = this.theStream.getMaxResolution(maxKbps, p_152834_2_, p_152834_3_, p_152834_4_);
        VideoParams videoparams = new VideoParams();
        videoparams.maxKbps = maxKbps;
        videoparams.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_HIGH;
        videoparams.pixelFormat = this.getPixelFormat();
        videoparams.targetFps = p_152834_2_;
        videoparams.outputWidth = aint[0];
        videoparams.outputHeight = aint[1];
        videoparams.disableAdaptiveBitrate = false;
        videoparams.verticalFlip = false;
        return videoparams;
    }

    public boolean func_152836_a(VideoParams p_152836_1_) {
        if (p_152836_1_ != null && this.isReadyToBroadcast()) {
            this.videoParamaters = p_152836_1_.clone();
            this.audioParamaters = new AudioParams();
            this.audioParamaters.enableMicCapture = this.audioParamaters.audioEnabled = this.field_152871_g && this.func_152848_y();
            this.audioParamaters.enablePlaybackCapture = this.audioParamaters.audioEnabled;
            this.audioParamaters.enablePassthroughAudio = false;
            if (!this.func_152823_L()) {
                this.videoParamaters = null;
                this.audioParamaters = null;
                return false;
            }
            ErrorCode errorcode = this.theStream.start(p_152836_1_, this.audioParamaters, this.ingestServ, StartFlags.None, true);
            if (ErrorCode.failed((ErrorCode)errorcode)) {
                this.func_152831_M();
                String s = ErrorCode.getString((ErrorCode)errorcode);
                this.logError(String.format("Error while starting to broadcast: %s", s));
                this.videoParamaters = null;
                this.audioParamaters = null;
                return false;
            }
            this.func_152827_a(BroadcastState.Starting);
            return true;
        }
        return false;
    }

    public boolean stopBroadcasting() {
        if (!this.isBroadcasting()) {
            return false;
        }
        ErrorCode errorcode = this.theStream.stop(true);
        if (ErrorCode.failed((ErrorCode)errorcode)) {
            String s = ErrorCode.getString((ErrorCode)errorcode);
            this.logError(String.format("Error while stopping the broadcast: %s", s));
            return false;
        }
        this.func_152827_a(BroadcastState.Stopping);
        return ErrorCode.succeeded((ErrorCode)errorcode);
    }

    public boolean func_152847_F() {
        if (!this.isBroadcasting()) {
            return false;
        }
        ErrorCode errorcode = this.theStream.pauseVideo();
        if (ErrorCode.failed((ErrorCode)errorcode)) {
            this.stopBroadcasting();
            String s = ErrorCode.getString((ErrorCode)errorcode);
            this.logError(String.format("Error pausing stream: %s\n", s));
        } else {
            this.func_152827_a(BroadcastState.Paused);
        }
        return ErrorCode.succeeded((ErrorCode)errorcode);
    }

    public boolean func_152854_G() {
        if (!this.isBroadcastPaused()) {
            return false;
        }
        this.func_152827_a(BroadcastState.Broadcasting);
        return true;
    }

    public boolean func_152840_a(String p_152840_1_, long p_152840_2_, String p_152840_4_, String p_152840_5_) {
        ErrorCode errorcode = this.theStream.sendActionMetaData(this.authenticationToken, p_152840_1_, p_152840_2_, p_152840_4_, p_152840_5_);
        if (ErrorCode.failed((ErrorCode)errorcode)) {
            String s = ErrorCode.getString((ErrorCode)errorcode);
            this.logError(String.format("Error while sending meta data: %s\n", s));
            return false;
        }
        return true;
    }

    public long func_177946_b(String p_177946_1_, long p_177946_2_, String p_177946_4_, String p_177946_5_) {
        long i = this.theStream.sendStartSpanMetaData(this.authenticationToken, p_177946_1_, p_177946_2_, p_177946_4_, p_177946_5_);
        if (i == -1L) {
            this.logError(String.format("Error in SendStartSpanMetaData\n", new Object[0]));
        }
        return i;
    }

    public boolean func_177947_a(String p_177947_1_, long p_177947_2_, long p_177947_4_, String p_177947_6_, String p_177947_7_) {
        if (p_177947_4_ == -1L) {
            this.logError(String.format("Invalid sequence id: %d\n", p_177947_4_));
            return false;
        }
        ErrorCode errorcode = this.theStream.sendEndSpanMetaData(this.authenticationToken, p_177947_1_, p_177947_2_, p_177947_4_, p_177947_6_, p_177947_7_);
        if (ErrorCode.failed((ErrorCode)errorcode)) {
            String s = ErrorCode.getString((ErrorCode)errorcode);
            this.logError(String.format("Error in SendStopSpanMetaData: %s\n", s));
            return false;
        }
        return true;
    }

    protected void func_152827_a(BroadcastState p_152827_1_) {
        if (p_152827_1_ != this.broadcastState) {
            this.broadcastState = p_152827_1_;
            try {
                if (this.broadcastListener != null) {
                    this.broadcastListener.func_152891_a(p_152827_1_);
                }
            }
            catch (Exception exception) {
                this.logError(exception.toString());
            }
        }
    }

    public void func_152821_H() {
        if (this.theStream != null && this.field_152876_l) {
            ErrorCode errorcode = this.theStream.pollTasks();
            this.func_152853_a(errorcode);
            if (this.isIngestTesting()) {
                this.ingestServTester.func_153041_j();
                if (this.ingestServTester.func_153032_e()) {
                    this.ingestServTester = null;
                    this.func_152827_a(BroadcastState.ReadyToBroadcast);
                }
            }
            switch (this.broadcastState.ordinal()) {
                case 3: {
                    this.func_152827_a(BroadcastState.LoggingIn);
                    errorcode = this.theStream.login(this.authenticationToken);
                    if (!ErrorCode.failed((ErrorCode)errorcode)) break;
                    String s3 = ErrorCode.getString((ErrorCode)errorcode);
                    this.logError(String.format("Error in TTV_Login: %s\n", s3));
                    break;
                }
                case 5: {
                    this.func_152827_a(BroadcastState.FindingIngestServer);
                    errorcode = this.theStream.getIngestServers(this.authenticationToken);
                    if (!ErrorCode.failed((ErrorCode)errorcode)) break;
                    this.func_152827_a(BroadcastState.LoggedIn);
                    String s2 = ErrorCode.getString((ErrorCode)errorcode);
                    this.logError(String.format("Error in TTV_GetIngestServers: %s\n", s2));
                    break;
                }
                case 7: {
                    this.func_152827_a(BroadcastState.ReadyToBroadcast);
                    errorcode = this.theStream.getUserInfo(this.authenticationToken);
                    if (ErrorCode.failed((ErrorCode)errorcode)) {
                        String s = ErrorCode.getString((ErrorCode)errorcode);
                        this.logError(String.format("Error in TTV_GetUserInfo: %s\n", s));
                    }
                    this.func_152835_I();
                    errorcode = this.theStream.getArchivingState(this.authenticationToken);
                    if (ErrorCode.failed((ErrorCode)errorcode)) {
                        String s1 = ErrorCode.getString((ErrorCode)errorcode);
                        this.logError(String.format("Error in TTV_GetArchivingState: %s\n", s1));
                    }
                }
                default: {
                    break;
                }
                case 10: 
                case 12: {
                    this.func_152835_I();
                }
            }
        }
    }

    protected void func_152835_I() {
        long i = System.nanoTime();
        long j = (i - this.field_152890_z) / 1000000000L;
        if (j >= 30L) {
            this.field_152890_z = i;
            ErrorCode errorcode = this.theStream.getStreamInfo(this.authenticationToken, this.field_152880_p);
            if (ErrorCode.failed((ErrorCode)errorcode)) {
                String s = ErrorCode.getString((ErrorCode)errorcode);
                this.logError(String.format("Error in TTV_GetStreamInfo: %s", s));
            }
        }
    }

    public IngestServerTester func_152838_J() {
        if (this.isReadyToBroadcast() && this.ingestList != null) {
            if (this.isIngestTesting()) {
                return null;
            }
            this.ingestServTester = new IngestServerTester(this.theStream, this.ingestList);
            this.ingestServTester.func_176004_j();
            this.func_152827_a(BroadcastState.IngestTesting);
            return this.ingestServTester;
        }
        return null;
    }

    protected boolean func_152823_L() {
        for (int i = 0; i < 3; ++i) {
            FrameBuffer framebuffer = this.theStream.allocateFrameBuffer(this.videoParamaters.outputWidth * this.videoParamaters.outputHeight * 4);
            if (!framebuffer.getIsValid()) {
                this.logError(String.format("Error while allocating frame buffer", new Object[0]));
                return false;
            }
            this.field_152874_j.add(framebuffer);
            this.field_152875_k.add(framebuffer);
        }
        return true;
    }

    protected void func_152831_M() {
        for (int i = 0; i < this.field_152874_j.size(); ++i) {
            FrameBuffer framebuffer = this.field_152874_j.get(i);
            framebuffer.free();
        }
        this.field_152875_k.clear();
        this.field_152874_j.clear();
    }

    public FrameBuffer func_152822_N() {
        if (this.field_152875_k.size() == 0) {
            this.logError(String.format("Out of free buffers, this should never happen", new Object[0]));
            return null;
        }
        FrameBuffer framebuffer = this.field_152875_k.get(this.field_152875_k.size() - 1);
        this.field_152875_k.remove(this.field_152875_k.size() - 1);
        return framebuffer;
    }

    public void captureFramebuffer(FrameBuffer p_152846_1_) {
        try {
            this.theStream.captureFrameBuffer_ReadPixels(p_152846_1_);
        }
        catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Trying to submit a frame to Twitch");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Broadcast State");
            crashreportcategory.addCrashSection("Last reported errors", Arrays.toString(field_152862_C.func_152756_c()));
            crashreportcategory.addCrashSection("Buffer", p_152846_1_);
            crashreportcategory.addCrashSection("Free buffer count", this.field_152875_k.size());
            crashreportcategory.addCrashSection("Capture buffer count", this.field_152874_j.size());
            throw new ReportedException(crashreport);
        }
    }

    public ErrorCode submitStreamFrame(FrameBuffer frame) {
        if (this.isBroadcastPaused()) {
            this.func_152854_G();
        } else if (!this.isBroadcasting()) {
            return ErrorCode.TTV_EC_STREAM_NOT_STARTED;
        }
        ErrorCode errorcode = this.theStream.submitVideoFrame(frame);
        if (errorcode != ErrorCode.TTV_EC_SUCCESS) {
            String s = ErrorCode.getString((ErrorCode)errorcode);
            if (ErrorCode.succeeded((ErrorCode)errorcode)) {
                this.logWarning(String.format("Warning in SubmitTexturePointer: %s\n", s));
            } else {
                this.logError(String.format("Error in SubmitTexturePointer: %s\n", s));
                this.stopBroadcasting();
            }
            if (this.broadcastListener != null) {
                this.broadcastListener.func_152893_b(errorcode);
            }
        }
        return errorcode;
    }

    protected boolean func_152853_a(ErrorCode p_152853_1_) {
        if (ErrorCode.failed((ErrorCode)p_152853_1_)) {
            this.logError(ErrorCode.getString((ErrorCode)p_152853_1_));
            return false;
        }
        return true;
    }

    protected void logError(String error) {
        this.lastError = error;
        field_152862_C.func_152757_a("<Error> " + error);
        logger.error(TwitchStream.STREAM_MARKER, "[Broadcast controller] {}", new Object[]{error});
    }

    protected void logWarning(String warning) {
        field_152862_C.func_152757_a("<Warning> " + warning);
        logger.warn(TwitchStream.STREAM_MARKER, "[Broadcast controller] {}", new Object[]{warning});
    }

    public static interface BroadcastListener {
        public void func_152900_a(ErrorCode var1, AuthToken var2);

        public void func_152897_a(ErrorCode var1);

        public void func_152898_a(ErrorCode var1, GameInfo[] var2);

        public void func_152891_a(BroadcastState var1);

        public void func_152895_a();

        public void func_152894_a(StreamInfo var1);

        public void func_152896_a(IngestList var1);

        public void func_152893_b(ErrorCode var1);

        public void func_152899_b();

        public void func_152901_c();

        public void func_152892_c(ErrorCode var1);
    }

    public static enum BroadcastState {
        Uninitialized,
        Initialized,
        Authenticating,
        Authenticated,
        LoggingIn,
        LoggedIn,
        FindingIngestServer,
        ReceivedIngestServers,
        ReadyToBroadcast,
        Starting,
        Broadcasting,
        Stopping,
        Paused,
        IngestTesting;

    }
}

