# Burning Energy

**Burning Energy** allows mods that produce and transfer energy through the [**Fabric Energy API**](https://github.com/TechReborn/Energy) to transfer it to every furnace-like block [**Burning**](https://github.com/NivOridocs/burning) recognizes, and optionally, vice versa with burning fuel and energy storages.

For instance, you can use any energy generator to power a good old vanilla **Furnace**.

## Configurations

Burning Energy only has two configurations.

```json
"enableEnergyToBurning": true // enabled by default
```

This will enable energy mods to transfer energy to any burning storage.

```json
"enableBurningToEnergy": false // disabled by default
```

This will enable burning mods to transfer burning fuel to any energy storage.
