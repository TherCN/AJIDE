/**
 * Copyright (c) 2016-2018 TypeFox and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */
package org.eclipse.lsp4j;

/**
 * Describes the content type that a client supports in various
 * result literals like {@link Hover}, {@link ParameterInformation} or {@link CompletionItem}.
 * <p>
 * Please note that {@code MarkupKind}s must not start with a {@code $}. These kinds
 * are reserved for internal usage.
 * <p>
 * Since 3.3.0
 */
@SuppressWarnings("all")
public final class MarkupKind {
  /**
   * Plain text is supported as a content format.
   */
  public static final String PLAINTEXT = "plaintext";

  /**
   * Markdown is supported as a content format.
   */
  public static final String MARKDOWN = "markdown";

  private MarkupKind() {
  }
}
