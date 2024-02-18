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
 * The kind of failure handling supported by the client.
 * <p>
 * Since 3.13.0
 */
@SuppressWarnings("all")
public final class FailureHandlingKind {
  /**
   * Applying the workspace change is simply aborted if one of the changes
   * provided fails. All operations executed before the failing operation stay
   * executed.
   */
  public static final String Abort = "abort";

  /**
   * All operations are executed transactional. That means they either all succeed
   * or no changes at all are applied to the workspace.
   */
  public static final String Transactional = "transactional";

  /**
   * If the workspace edit contains only textual file changes they are executed
   * transactional. If resource changes (create, rename or delete file) are part
   * of the change the failure handling strategy is abort.
   */
  public static final String TextOnlyTransactional = "textOnlyTransactional";

  /**
   * The client tries to undo the operations already executed. But there is no
   * guarantee that this is succeeding.
   */
  public static final String Undo = "undo";

  private FailureHandlingKind() {
  }
}
