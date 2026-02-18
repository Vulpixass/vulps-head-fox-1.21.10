package net.vulpixass.headfox.command.client;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.vulpixass.headfox.client.render.FoxHatFeatureRenderer;

public class ClientCommands {
    public static FoxTypesEnum FoxType = FoxTypesEnum.REGULAR;
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("foxAppearance")
                .then(ClientCommandManager.argument("type", StringArgumentType.word())
                        .suggests(((context, builder) -> {
                            builder.suggest("regular");
                            builder.suggest("snow");
                            return builder.buildFuture();
                        }))
                        .executes(ctx -> {
                                String type = StringArgumentType.getString(ctx, "type");
                                switch (type) {
                                    case "regular": FoxType = FoxTypesEnum.REGULAR; return 1;
                                    case "snow": FoxType = FoxTypesEnum.SNOW; return 1;
                                }
                                return 0;
                        })));
    }
}
