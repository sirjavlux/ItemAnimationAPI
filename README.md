# ItemAnimationAPI
A plugin made for making packet based item animations!

The plugin is in early stages, more updates will come soon!

### Creation of moving item
This will create a moving item for the selected player.
```
MovingItem movingItem = new MovingItem(Player p, ItemStack itemStack, Location location); 
```

### Using the created moving item
Used for moving the moving item to another location
```
movingItem.teleport(Location location);
```

Used for despawning moving item

```
movingItem.despawn();
```

Used for changing item

```
movingItem.setItemStack(ItemStack item);
```

![AnimationPreview](https://media.giphy.com/media/ZYPsEqYdFMbEW3xBjH/giphy.gif)
