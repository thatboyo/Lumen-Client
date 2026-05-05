# Lumen-Client
A sleek modern Minecraft Ghost Client designed for Fabric 1.21.x


Lumen Client

Minimalist ghost style utility client for Minecraft 1.21.x (Fabric)

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

Drop lumen-client.jar into:

.minecraft/mods/
Start Minecraft
🎮 Controls
Key	Action
RSHIFT	ClickGUI
P	ClickHUD (hide HUD + render modules)
(custom)	Module keybinds are customizable
 Core Systems
Main Entry

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

Stealthy automation features:

Silent Aura
Auto Hit
Reach
Velocity / AntiKB
AutoClicker
Crits
AutoPotion
Render

Visual enhancements:

ESP / Tracers / Chams
Nametags
Hitboxes
BlockESP
ViewModel
Movement

Movement improvements:

Sprint / Speed / Fly
No Slow
Scaffold
Strafe
Ghost

Mirroring + unique stealth features:

Blink
TimerHack
Anti Aim
Disabler
MLG Water
Ghost Scaffold
Player (HUD)

Customizable HUD elements:

Arraylist
Watermark (custom placeholder supported)
Coordinates
FPS / Ping
Keystrokes / CPS
Armor HUD
Clock
 Legit

Quality of Life Improvements Only:

Full Bright
Zoom
Free Cam
No Weather
Timechanger
Name Protector
Options

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

Lumen tries to avoid doing things too obviously:

Executes in a probabilistic fashion
Guarantees visible movements are legitimate
Emphasizes timing rather than automation

also known as:
Not rage, just "huh, that kinda looked legit..." 💀

 Development Details

Utilizes mixins for event injection points
Rendering uses Fabric Rendering API
Configuration uses Gson
Built with extensibility and separation in mind
 Watermark Placeholder

The watermark feature allows for image use, however, it is currently hardcoded to text rendering:

// TODO: Replace with watermark texture when asset is provided
drawText("Lumen v1.0 | 1.21.x");
⚠️ Disclaimer

Lumen Client is designed for:

Anticheat testing
Personal use only

Lumen Client is not intended for server use.
