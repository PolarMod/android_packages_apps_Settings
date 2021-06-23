/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.biometrics.face;

import static android.hardware.biometrics.BiometricAuthenticator.TYPE_FACE;

import android.app.settings.SettingsEnums;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.android.settings.R;

/**
 * Displays parental consent information for face authentication.
 */
public class FaceEnrollParentalConsent extends FaceEnrollIntroduction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setDescriptionText(R.string.security_settings_face_enroll_introduction_consent_message);
    }

    @Override
    protected void onNextButtonClick(View view) {
        onConsentResult(true /* granted */);
    }

    @Override
    protected void onSkipButtonClick(View view) {
        onConsentResult(false /* granted */);
    }

    private void onConsentResult(boolean granted) {
        final Intent result = new Intent();
        result.putExtra(EXTRA_KEY_MODALITY, TYPE_FACE);
        setResult(granted ? RESULT_CONSENT_GRANTED : RESULT_CONSENT_DENIED, result);
        finish();
    }

    @Override
    protected boolean onSetOrConfirmCredentials(@Nullable Intent data) {
        // prevent challenge from being generated by default
        return true;
    }

    @Override
    protected boolean generateChallengeOnCreate() {
        return false;
    }

    @Override
    @StringRes
    protected int getInfoMessageGlasses() {
        return R.string.security_settings_face_enroll_introduction_info_consent_glasses;
    }

    @Override
    @StringRes
    protected int getInfoMessageLooking() {
        return R.string.security_settings_face_enroll_introduction_info_consent_looking;
    }

    @Override
    @StringRes
    protected int getHowMessage() {
        return R.string.security_settings_face_enroll_introduction_how_consent_message;
    }

    @Override
    @StringRes
    protected int getInControlTitle() {
        return R.string.security_settings_face_enroll_introduction_control_consent_title;
    }

    @Override
    @StringRes
    protected int getInControlMessage() {
        return R.string.security_settings_face_enroll_introduction_control_consent_message;
    }

    @Override
    protected int getHeaderResDefault() {
        return R.string.security_settings_face_enroll_consent_introduction_title;
    }

    @Override
    public int getMetricsCategory() {
        return SettingsEnums.FACE_PARENTAL_CONSENT;
    }
}
