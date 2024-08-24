package net.minecraft.network.play;

import net.minecraft.network.INetHandler;
import Rename.Class371;
import Rename.Class411;
import Rename.Class420;
import Rename.Class341;
import Rename.Class376;
import Rename.Class400;
import Rename.Class398;
import Rename.Class401;
import Rename.Class351;
import Rename.Class374;
import Rename.Class338;
import Rename.Class367;
import Rename.Class368;
import Rename.Class369;
import Rename.Class339;
import Rename.Class345;
import Rename.Class418;
import Rename.Class373;
import Rename.Class349;
import Rename.Class402;
import Rename.Class397;
import Rename.Class406;
import Rename.Class404;
import Rename.Class415;
import Rename.Class370;
import Rename.Class419;
import Rename.Class347;
import Rename.Class407;
import Rename.Class344;
import Rename.Class388;
import Rename.Class386;
import Rename.Class394;
import Rename.Class382;
import Rename.Class380;
import Rename.Class383;
import Rename.Class396;
import Rename.Class384;
import Rename.Class381;
import Rename.Class389;
import Rename.Class399;
import Rename.Class342;
import Rename.Class346;
import Rename.Class403;
import Rename.Class340;
import Rename.Class378;
import Rename.Class393;
import Rename.Class390;
import Rename.Class391;
import Rename.Class405;
import Rename.Class395;
import Rename.Class385;
import Rename.Class408;
import Rename.Class392;
import Rename.Class412;
import Rename.Class417;
import Rename.Class348;
import Rename.Class379;
import Rename.Class350;
import Rename.Class372;
import Rename.Class375;
import Rename.Class377;
import Rename.Class413;
import Rename.Class416;
import Rename.Class422;
import Rename.Class410;
import Rename.Class387;
import Rename.Class414;
import Rename.Class421;
import Rename.Class409;
import Rename.Class423;
import Rename.Class424;

public interface INetHandlerPlayClient
extends INetHandler {
    public void handleSpawnObject(Class339 var1);

    public void handleSpawnExperienceOrb(Class373 var1);

    public void handleSpawnGlobalEntity(Class346 var1);

    public void handleSpawnMob(Class345 var1);

    public void handleScoreboardObjective(Class379 var1);

    public void handleSpawnPainting(Class418 var1);

    public void handleSpawnPlayer(Class368 var1);

    public void handleAnimation(Class367 var1);

    public void handleStatistics(Class392 var1);

    public void handleBlockBreakAnim(Class383 var1);

    public void handleSignEditorOpen(Class408 var1);

    public void handleUpdateTileEntity(Class385 var1);

    public void handleBlockAction(Class380 var1);

    public void handleBlockChange(Class382 var1);

    public void handleChat(Class420 var1);

    public void handleTabComplete(Class348 var1);

    public void handleMultiBlockChange(Class394 var1);

    public void handleMaps(Class395 var1);

    public void handleConfirmTransaction(Class391 var1);

    public void handleCloseWindow(Class340 var1);

    public void handleWindowItems(Class393 var1);

    public void handleOpenWindow(Class403 var1);

    public void handleWindowProperty(Class390 var1);

    public void handleSetSlot(Class378 var1);

    public void handleCustomPayload(Class377 var1);

    public void handleDisconnect(Class413 var1);

    public void handleUseBed(Class338 var1);

    public void handleEntityStatus(Class415 var1);

    public void handleEntityAttach(Class370 var1);

    public void handleExplosion(Class384 var1);

    public void handleChangeGameState(Class342 var1);

    public void handleKeepAlive(Class371 var1);

    public void handleChunkData(Class386 var1);

    public void handleMapChunkBulk(Class396 var1);

    public void handleEffect(Class381 var1);

    public void handleJoinGame(Class411 var1);

    public void handleEntityMovement(Class397 var1);

    public void handlePlayerPosLook(Class351 var1);

    public void handleParticles(Class399 var1);

    public void handlePlayerAbilities(Class417 var1);

    public void handlePlayerListItem(Class412 var1);

    public void handleDestroyEntities(Class402 var1);

    public void handleRemoveEntityEffect(Class407 var1);

    public void handleRespawn(Class401 var1);

    public void handleEntityHeadLook(Class404 var1);

    public void handleHeldItemChange(Class374 var1);

    public void handleDisplayScoreboard(Class372 var1);

    public void handleEntityMetadata(Class419 var1);

    public void handleEntityVelocity(Class349 var1);

    public void handleEntityEquipment(Class376 var1);

    public void handleSetExperience(Class344 var1);

    public void handleUpdateHealth(Class398 var1);

    public void handleTeams(Class375 var1);

    public void handleUpdateScore(Class350 var1);

    public void handleSpawnPosition(Class400 var1);

    public void handleTimeUpdate(Class341 var1);

    public void handleUpdateSign(Class405 var1);

    public void handleSoundEffect(Class389 var1);

    public void handleCollectItem(Class369 var1);

    public void handleEntityTeleport(Class406 var1);

    public void handleEntityProperties(Class388 var1);

    public void handleEntityEffect(Class347 var1);

    public void handleCombatEvent(Class422 var1);

    public void handleServerDifficulty(Class416 var1);

    public void handleCamera(Class410 var1);

    public void handleWorldBorder(Class387 var1);

    public void handleTitle(Class414 var1);

    public void handleSetCompressionLevel(Class421 var1);

    public void handlePlayerListHeaderFooter(Class409 var1);

    public void handleResourcePack(Class423 var1);

    public void handleEntityNBT(Class424 var1);
}

