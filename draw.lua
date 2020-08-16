local string = require("string")
local os = require("os")
local filesystem = require("filesystem")
local component = require("component")
local command_block = component.command_block
local args = {...}
local blocks = {}
blocks[0] = "minecraft:wool 0"
blocks[1] = "minecraft:wool 1"
blocks[2] = "minecraft:wool 2"
blocks[3] = "minecraft:wool 3"
blocks[4] = "minecraft:wool 4"
blocks[5] = "minecraft:wool 5"
blocks[6] = "minecraft:wool 6"
blocks[7] = "minecraft:wool 7"
blocks[8] = "minecraft:wool 8"
blocks[9] = "minecraft:wool 9"
blocks[10] = "minecraft:wool 10"
blocks[11] = "minecraft:wool 11"
blocks[12] = "minecraft:wool 12"
blocks[13] = "minecraft:wool 13"
blocks[14] = "minecraft:wool 14"
blocks[15] = "minecraft:wool 15"

local filesize = filesystem.size(args[1])
local file = filesystem.open(args[1], "r")
local content = file:read(filesize)
for i = 1, filesize do
	local id = string.byte(content, i)
	command_block.setCommand("/setblock ~ ~3 ~ " .. blocks[id])
	command_block.executeCommand()
	os.sleep(0.1)
end