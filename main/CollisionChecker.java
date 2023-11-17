package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        //four corner detection
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBOttomRow = entityBottomWorldY/gp.tileSize;
        int tileNum1,tileNum2;
        System.out.println(entityLeftCol+" "+entityRightCol+" "+entityTopRow);
        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = Integer.parseInt(gp.tileManager.mapTileNum[entityTopRow][entityLeftCol]);
                tileNum2 = Integer.parseInt(gp.tileManager.mapTileNum[entityTopRow][entityRightCol]);
                System.out.println(tileNum1+" "+tileNum2);
                if(gp.tileManager.tile[tileNum1].collision && gp.tileManager.tile[tileNum2].collision){
                    entity.collisonOn = true;
                }
                break;
            case "down":
                entityBOttomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = Integer.parseInt(gp.tileManager.mapTileNum[entityBOttomRow][entityLeftCol]);
                tileNum2 = Integer.parseInt(gp.tileManager.mapTileNum[entityBOttomRow][entityRightCol]);
                System.out.println(tileNum1+" "+tileNum2);
                if(gp.tileManager.tile[tileNum1].collision && gp.tileManager.tile[tileNum2].collision){
                    entity.collisonOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = Integer.parseInt(gp.tileManager.mapTileNum[entityTopRow][entityLeftCol]);
                tileNum2 = Integer.parseInt(gp.tileManager.mapTileNum[entityBOttomRow][entityLeftCol]);
                System.out.println(tileNum1+" "+tileNum2);
                if(gp.tileManager.tile[tileNum1].collision && gp.tileManager.tile[tileNum2].collision){
                    entity.collisonOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX - entity.speed)/gp.tileSize;
                tileNum1 = Integer.parseInt(gp.tileManager.mapTileNum[entityTopRow][entityRightCol]);
                tileNum2 = Integer.parseInt(gp.tileManager.mapTileNum[entityBOttomRow][entityRightCol]);
                System.out.println(tileNum1+" "+tileNum2);
                if(gp.tileManager.tile[tileNum1].collision && gp.tileManager.tile[tileNum2].collision){
                    entity.collisonOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for(int i = 0; i<gp.obj.length;i++){

            if(gp.obj[i] != null){
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;

                gp.obj[i].solidArea.x += gp.obj[i].worldX;
                gp.obj[i].solidArea.y += gp.obj[i].worldY;

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision){
                                entity.collisonOn = true;
                            }
                            if(player){
                                index = 1;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision){
                                entity.collisonOn = true;
                            }
                            if(player){
                                index = 1;
                            }
                        }                    
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision){
                                entity.collisonOn = true;
                            }
                            if(player){
                                index = 1;
                            }
                        }                    
                        break;
                    case "right":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision){
                                entity.collisonOn = true;
                            }
                            if(player){
                                index = 1;
                            }
                        }                    
                        break;

                }
            }
        }
        return index;
    }
}
