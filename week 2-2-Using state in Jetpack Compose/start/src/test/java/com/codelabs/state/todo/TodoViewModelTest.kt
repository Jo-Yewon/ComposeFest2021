/*
 * Copyright (C) 2020 The Android Open Source Project
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

package com.codelabs.state.todo

import com.codelabs.state.util.generateRandomTodoItem
import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * 테스트가 더 편리해진 이유가 무엇일까?
 * 기존에 ui 랑 비즈니스 로직이 섞여있는 경우에는, removeItem() 하면 거기에 UI 관련 로직도 들어있을거기 때문에...
 * UI 없이는 테스트가 안됨.
 * 근데 이제 됨.
 */
class TodoViewModelTest {

    @Test
    fun whenRemovingItem_updatesList() {
        // before
        val viewModel = TodoViewModel()
        val item1 = generateRandomTodoItem()
        val item2 = generateRandomTodoItem()
        viewModel.addItem(item1)
        viewModel.addItem(item2)

        // during
        viewModel.removeItem(item1)

        // after
        assertThat(viewModel.todoItems).isEqualTo(listOf(item2))
    }
}
