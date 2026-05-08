# Lumen-Client
A sleek modern Minecraft Ghost Client designed for Fabric 1.21.x
==================================================================
 (NOTE!) this is not finished yet, so it might be buggy
Overview   

Lumen Client is a lightweight, modular Fabric client created for anticheat testing on private servers.
The client features minimalistic visuals, hidden mechanics, full modularization and a ghost style theme.

 
 Built with Fabric 1.21.x + Java 21
 Modular architecture with an event-based system
 Fully themeable user interface (Ghost/Cyber/Hacker)
 Profile system
 Two clickgui styles
 Installation
 Install Fabric Loader (1.21.x)
 Install Fabric API
 
===================================================
to build the client:
run ./gradlew build
------------------------------------

Drop lumen-client.jar into:

.minecraft/mods/,
Start Minecraft,

-----------------------
Default Keybinds are:
RSHIFT	for ClickGUI
P	for ClickHUD (hide HUD + render modules),
also,
custom	module keybinds are customizable

-------------------------------------
LumenClient

Inits all managers:

ThemeManager → EventBus → ModuleManager → HudManager → ConfigManager → GuiManager
Event System

Custom lightweight event bus.

Key events:

EventTick — called each tick
EventRender2D — HUD rendering
EventRender3D — rendering 3d world
EventKey — key events
EventPacket — sending and receiving packets
EventMotion — motion events
Modules

All features are toggleable modules.

Base class structure:

name, description, category
keybind, enabled
settings[]

Lifecycle methods:

onEnable() -> subscribes to events
onDisable() -> unsubscribes from events
toggle() -> toggles module state
Settings System

Generic and extensible:

BooleanSetting
SliderSetting
ChanceSetting (logic of ghost modules 👀)
EnumSetting
ColorSetting
KeybindSetting
StringSetting
Themes



-----------------------------------------
Toggle instantly through config:

 Ghost (Default)
Black & white design
White highlight color
Simple UI
Cyber
Blue glow effect
Dark futuristic theme
Hacker
Green theme design
Retro hacker UI
Modules
 Favorite Modules
Modules star by user
Instantly toggle favorite modules between categories
 Combat


--------------------------------
Stealthy automation features:

Silent Aura
Auto Hit
Reach
Velocity / AntiKB
AutoClicker
Crits
AutoPotion
Render
-------------------------
Visual enhancements:

ESP / Tracers / Chams
Nametags
Hitboxes
BlockESP
ViewModel
Movement

------------------------------

Movement improvements:

Sprint / Speed / Fly
No Slow
Scaffold
Strafe
Ghost


-----------------------------------
Mirroring + unique stealth features:

Blink
TimerHack
Anti Aim
Disabler
MLG Water
Ghost Scaffold
Player (HUD)


------------------------------
Customizable HUD elements:

Arraylist
Watermark (custom placeholder supported)
Coordinates
FPS / Ping
Keystrokes / CPS
Armor HUD
Clock
Legit

----------------------------------- 

Quality of Life Improvements Only:

Full Bright
Zoom
Free Cam
No Weather
Timechanger
Name Protector
Options

---------------------------

Universal configuration:

GUI Theme (Classic / Sidebar)
Theme changer
Animation speed
FPS cap in GUI
Profiles manager
 ClickGUI
Classic
Category panels are draggable
Expandable modules
Scrollable
Sidebar
Fixed Layout
Categories to the left
Modules to the right
Config System

Path:

.minecraft/lumen/
.minecraft/lumen/profiles/

================================

Features:

Auto-save upon exit
Profile switching
State Persistence:
Modules
Settings
HUD positions
Keybinds
Theme/Style

How Lumen Works (Flow)

Main game loop → Mixins handle event injections
        ↓
Events are dispatched through the EventBus
        ↓
Modules process events
        ↓
HUD rendering (2D)
Render module rendering (3D)
        ↓
Settings save upon exit
 Philosophy Behind Lumen

Lumen tries to avoid blatant cheating:

Executes in a probabilistic fashion
Guarantees visible movements are legitimate
Emphasizes timing rather than automation


----------------------------------------------
Development Details

Utilizes mixins for event injection points
Rendering uses Fabric Rendering API
Configuration uses Gson
Built with extensibility and separation in mind
 Watermark Placeholder

The watermark feature will be implemented soon, as soon as i can settle on a design:

=====================================================================================
// TODO: 
*Replace with watermark texture when asset is provided
*finish the modules
*playtest the ghost features, 
*find the most optimal chance settings to set to default


----------------------------------
⚠️Disclaimer

Lumen Client is designed for:

Quality of Life,
Ghost Cheating
AntiCheat Testing

cranking up the settings to much could get you banned from public server
use with caution
