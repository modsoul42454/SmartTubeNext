package com.liskovsoft.smartyoutubetv2.common.prefs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import com.liskovsoft.sharedutils.helpers.Helpers;
import com.liskovsoft.smartyoutubetv2.common.R;
import com.liskovsoft.smartyoutubetv2.common.utils.ClickbaitRemover;

import java.util.ArrayList;
import java.util.List;

public class MainUIData {
    private static final String MAIN_UI_DATA = "main_ui_data2";
    public static final int CHANNEL_SORTING_UPDATE = 0;
    public static final int CHANNEL_SORTING_AZ = 1;
    public static final int CHANNEL_SORTING_LAST_VIEWED = 2;
    public static final int PLAYLISTS_STYLE_GRID = 0;
    public static final int PLAYLISTS_STYLE_ROWS = 1;
    public static final int MENU_ITEM_RECENT_PLAYLIST = 0b1;
    public static final int MENU_ITEM_ADD_TO_QUEUE = 0b10;
    public static final int MENU_ITEM_PIN_TO_SIDEBAR = 0b100;
    public static final int MENU_ITEM_SHARE_LINK = 0b1000;
    public static final int MENU_ITEM_SELECT_ACCOUNT = 0b10000;
    public static final int MENU_ITEM_NOT_INTERESTED = 0b100000;
    public static final int MENU_ITEM_REMOVE_FROM_HISTORY = 0b1000000;
    public static final int MENU_ITEM_MOVE_SECTION_UP = 0b10000000;
    public static final int MENU_ITEM_MOVE_SECTION_DOWN = 0b100000000;
    public static final int MENU_ITEM_OPEN_DESCRIPTION = 0b1000000000;
    public static final int MENU_ITEM_RENAME_SECTION = 0b10000000000;
    public static final int MENU_ITEM_PLAY_VIDEO = 0b100000000000;
    public static final int MENU_ITEM_SAVE_PLAYLIST = 0b1000000000000;
    public static final int MENU_ITEM_ADD_TO_PLAYLIST = 0b10000000000000;
    public static final int MENU_ITEM_SUBSCRIBE = 0b100000000000000;
    public static final int MENU_ITEM_CREATE_PLAYLIST = 0b1000000000000000;
    public static final int MENU_ITEM_STREAM_REMINDER = 0b10000000000000000;
    public static final int MENU_ITEM_ADD_TO_NEW_PLAYLIST = 0b100000000000000000;
    public static final int MENU_ITEM_SHARE_EMBED_LINK = 0b1000000000000000000;
    public static final int MENU_ITEM_SHOW_QUEUE = 0b10000000000000000000;
    public static final int MENU_ITEM_PLAYLIST_ORDER = 0b100000000000000000000;
    public static final int BUTTON_BROWSE_ACCOUNTS = 0b1;
    public static final int BUTTON_CHANGE_LANGUAGE = 0b10;
    @SuppressLint("StaticFieldLeak")
    private static MainUIData sInstance;
    private final Context mContext;
    private final AppPrefs mPrefs;
    private boolean mIsCardAnimatedPreviewsEnabled;
    private boolean mIsCardMultilineTitleEnabled;
    private boolean mIsCardTextAutoScrollEnabled;
    private int mCardTitleLinesNum;
    private float mUIScale;
    private float mVideoGridScale;
    private final List<ColorScheme> mColorSchemes = new ArrayList<>();
    private int mColorSchemeIndex;
    private int mChannelCategorySorting;
    private int mPlaylistsStyle;
    private boolean mIsUploadsOldLookEnabled;
    private boolean mIsUploadsAutoLoadEnabled;
    private float mCardTextScrollSpeed;
    private int mMenuItems;
    private int mButtons;
    private int mThumbQuality;

    private MainUIData(Context context) {
        mContext = context;
        mPrefs = AppPrefs.instance(context);
        initColorSchemes();
        restoreState();
    }

    public static MainUIData instance(Context context) {
        if (sInstance == null) {
            sInstance = new MainUIData(context.getApplicationContext());
        }

        return sInstance;
    }

    public void enableCardAnimatedPreviews(boolean enable) {
        mIsCardAnimatedPreviewsEnabled = enable;
        persistState();
    }

    public boolean isCardAnimatedPreviewsEnabled() {
        return mIsCardAnimatedPreviewsEnabled;
    }

    public void enableCardMultilineTitle(boolean enable) {
        mIsCardMultilineTitleEnabled = enable;
        persistState();
    }

    public boolean isCardMultilineTitleEnabled() {
        return mIsCardMultilineTitleEnabled;
    }

    public void enableCardTextAutoScroll(boolean enable) {
        mIsCardTextAutoScrollEnabled = enable;
        persistState();
    }

    public boolean isCardTextAutoScrollEnabled() {
        return mIsCardTextAutoScrollEnabled;
    }

    public void setCartTitleLinesNum(int lines) {
        mCardTitleLinesNum = lines;
        persistState();
    }

    public int getCardTitleLinesNum() {
        return mCardTitleLinesNum;
    }

    public void setThumbQuality(int quality) {
        mThumbQuality = quality;
        persistState();
    }

    public int getThumbQuality() {
        return mThumbQuality;
    }

    public void setVideoGridScale(float scale) {
        mVideoGridScale = scale;

        persistState();
    }

    public float getVideoGridScale() {
        // Fixing the bug with chaotic cards positioning on Android 4.4 devices.
        return Build.VERSION.SDK_INT <= 19 ? 1.2f : mVideoGridScale;
    }

    public void setUIScale(float scale) {
        mUIScale = scale;

        persistState();
    }

    public float getUIScale() {
        return mUIScale;
    }

    public List<ColorScheme> getColorSchemes() {
        return mColorSchemes;
    }

    public void setColorScheme(ColorScheme scheme) {
        mColorSchemeIndex = mColorSchemes.indexOf(scheme);
        persistState();
    }

    public ColorScheme getColorScheme() {
        return mColorSchemes.get(mColorSchemeIndex);
    }

    public int getChannelCategorySorting() {
        return mChannelCategorySorting;
    }

    public void setChannelCategorySorting(int type) {
        mChannelCategorySorting = type;
        persistState();
    }

    public int getPlaylistsStyle() {
        return mPlaylistsStyle;
    }

    public void setPlaylistsStyle(int type) {
        mPlaylistsStyle = type;
        persistState();
    }

    public void enableUploadsOldLook(boolean enable) {
        mIsUploadsOldLookEnabled = enable;
        persistState();
    }

    public boolean isUploadsOldLookEnabled() {
        return mIsUploadsOldLookEnabled;
    }

    public void enableUploadsAutoLoad(boolean enable) {
        mIsUploadsAutoLoadEnabled = enable;
        persistState();
    }

    public boolean isUploadsAutoLoadEnabled() {
        return mIsUploadsAutoLoadEnabled;
    }

    public void setCardTextScrollSpeed(float factor) {
        mCardTextScrollSpeed = factor;

        enableCardTextAutoScroll(true);

        persistState();
    }

    public float getCardTextScrollSpeed() {
        return mCardTextScrollSpeed;
    }

    public void enableMenuItem(int menuItems) {
        mMenuItems |= menuItems;
        persistState();
    }

    public void disableMenuItem(int menuItems) {
        mMenuItems &= ~menuItems;
        persistState();
    }

    public boolean isMenuItemEnabled(int menuItems) {
        return (mMenuItems & menuItems) == menuItems;
    }

    public void enableButton(int button) {
        mButtons |= button;
        persistState();
    }

    public void disableButton(int button) {
        mButtons &= ~button;
        persistState();
    }

    public boolean isButtonEnabled(int button) {
        return (mButtons & button) == button;
    }

    private void initColorSchemes() {
        mColorSchemes.add(new ColorScheme(
                R.string.color_scheme_teal,
                null,
                null,
                null,
                mContext));
        mColorSchemes.add(new ColorScheme(
                R.string.color_scheme_dark_grey,
                "App.Theme.DarkGrey.Player",
                "App.Theme.DarkGrey.Browse",
                "App.Theme.DarkGrey.Preferences",
                mContext));
        mColorSchemes.add(new ColorScheme(
                R.string.color_scheme_red,
                "App.Theme.Red.Player",
                "App.Theme.Red.Browse",
                "App.Theme.Red.Preferences",
                mContext));
        mColorSchemes.add(new ColorScheme(
                R.string.color_scheme_dark_grey_oled,
                "App.Theme.DarkGrey.OLED.Player",
                "App.Theme.DarkGrey.OLED.Browse",
                "App.Theme.DarkGrey.Preferences",
                mContext));
        mColorSchemes.add(new ColorScheme(
                R.string.color_scheme_teal_oled,
                "App.Theme.Leanback.OLED.Player",
                "App.Theme.Leanback.OLED.Browse",
                null,
                mContext));
        mColorSchemes.add(new ColorScheme(
                R.string.color_scheme_dark_grey_monochrome,
                "App.Theme.DarkGrey.OLED.Player",
                "App.Theme.DarkGrey2.OLED.Browse",
                "App.Theme.DarkGrey.Preferences",
                mContext));
    }

    private void restoreState() {
        String data = mPrefs.getData(MAIN_UI_DATA);

        String[] split = Helpers.splitObjectLegacy(data);

        mIsCardAnimatedPreviewsEnabled = Helpers.parseBoolean(split, 0, true);
        mVideoGridScale = Helpers.parseFloat(split, 1, 1.0f); // 4 cards in a row
        mUIScale = Helpers.parseFloat(split, 2, 1.0f);
        mColorSchemeIndex = Helpers.parseInt(split, 3, 1);
        mIsCardMultilineTitleEnabled = Helpers.parseBoolean(split, 4, true);
        mChannelCategorySorting = Helpers.parseInt(split, 5, CHANNEL_SORTING_LAST_VIEWED);
        mPlaylistsStyle = Helpers.parseInt(split, 6, PLAYLISTS_STYLE_GRID);
        mCardTitleLinesNum = Helpers.parseInt(split, 7, 1);
        mIsCardTextAutoScrollEnabled = Helpers.parseBoolean(split, 8, true);
        mIsUploadsOldLookEnabled = Helpers.parseBoolean(split, 9, false);
        mIsUploadsAutoLoadEnabled = Helpers.parseBoolean(split, 10, true);
        mCardTextScrollSpeed = Helpers.parseFloat(split, 11, 2);
        mMenuItems = Helpers.parseInt(split, 12,
                Integer.MAX_VALUE & ~(MENU_ITEM_RECENT_PLAYLIST | MENU_ITEM_ADD_TO_NEW_PLAYLIST | MENU_ITEM_SELECT_ACCOUNT |
                        MENU_ITEM_PLAY_VIDEO | MENU_ITEM_OPEN_DESCRIPTION | MENU_ITEM_PIN_TO_SIDEBAR | MENU_ITEM_SHARE_EMBED_LINK)); // all except this items
        mButtons = Helpers.parseInt(split, 13, Integer.MAX_VALUE & ~(BUTTON_CHANGE_LANGUAGE)); // all except this items
        // 14
        mThumbQuality = Helpers.parseInt(split, 15, ClickbaitRemover.THUMB_QUALITY_DEFAULT);
    }

    private void persistState() {
        mPrefs.setData(MAIN_UI_DATA, Helpers.mergeObject(mIsCardAnimatedPreviewsEnabled,
                mVideoGridScale, mUIScale, mColorSchemeIndex, mIsCardMultilineTitleEnabled,
                mChannelCategorySorting, mPlaylistsStyle, mCardTitleLinesNum, mIsCardTextAutoScrollEnabled,
                mIsUploadsOldLookEnabled, mIsUploadsAutoLoadEnabled, mCardTextScrollSpeed, mMenuItems, mButtons,
                null, mThumbQuality));
    }

    public static class ColorScheme {
        public final int nameResId;
        public final int playerThemeResId;
        public final int browseThemeResId;
        public final int settingsThemeResId;

        public ColorScheme(int nameResId,
                           String playerTheme,
                           String browseTheme,
                           String settingsTheme,
                           Context context) {
            this.nameResId = nameResId;
            this.playerThemeResId = Helpers.getResourceId(playerTheme, "style", context);
            this.browseThemeResId = Helpers.getResourceId(browseTheme, "style", context);
            this.settingsThemeResId = Helpers.getResourceId(settingsTheme, "style", context);
        }
    }
}
